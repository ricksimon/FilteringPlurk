package cc.ricksimon.android.filteringplurk.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;

import com.google.jplurk_oauth.Qualifier;
import com.google.jplurk_oauth.module.Timeline;
import com.google.jplurk_oauth.module.Users;
import com.google.jplurk_oauth.skeleton.Args;
import com.google.jplurk_oauth.skeleton.RequestException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cc.ricksimon.android.filteringplurk.bean.BaseBean;
import cc.ricksimon.android.filteringplurk.bean.PlurkBean;
import cc.ricksimon.android.filteringplurk.bean.StatusBean;
import cc.ricksimon.android.filteringplurk.bean.TimeLineBean;
import cc.ricksimon.android.filteringplurk.bean.UserBean;
import cc.ricksimon.android.filteringplurk.oauth.PlurkOAuthCallback;
import cc.ricksimon.android.filteringplurk.utils.Log;
import cc.ricksimon.android.filteringplurk.utils.Util;

/**
 * Created by Simon on 2017/10/26.
 */

public class BaseActivity extends AppCompatActivity {

    public static final String TAG = BaseActivity.class.getSimpleName();

    public static final String EXTRA_PLURK_ID = "plurk_id";
    public static final String EXTRA_PLURK_CONTENT = "plurk_content";
    public static final String EXTRA_PLURK_VERB = "plurk_verb";

    public void getUserProfile(final PlurkOAuthCallback callback){
        AsyncTask task = new AsyncTask() {
            @Override
            protected UserBean doInBackground(Object[] params) {
                UserBean userBean = null;
                try{
                    JSONObject jsonObject = Util.getAuth(BaseActivity.this).using(Users.class).currUser();
                    Log.e(TAG,jsonObject.toString());

                    userBean = UserBean.parseFullUserBean(jsonObject);
                }catch(Exception e){
                    e.printStackTrace();
                }
                return userBean;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);

                if(o != null && o.getClass().getSimpleName().equals(UserBean.class.getSimpleName())){
                    callback.onAPICallBack((UserBean)o);
                }
            }
        };

        task.execute();
    }

    //TODO:arg for change mode
    public void getPlurks(final PlurkOAuthCallback callback){
        AsyncTask task = new AsyncTask() {
            @Override
            protected TimeLineBean doInBackground(Object[] objects) {
                TimeLineBean timeLineBean = null;
                try {
                    Args args = new Args();
                    args.add("filter","my");
                    JSONObject jsonObject = Util.getAuth(BaseActivity.this).using(Timeline.class).getPlurks(args);
//                    JSONObject jsonObject = Util.getAuth(BaseActivity.this).using(Timeline.class).getPlurks();
                    Log.e(TAG,jsonObject.toString());

                    timeLineBean = TimeLineBean.parseTimeLineBean(jsonObject);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return timeLineBean;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);

                if(o != null && o.getClass().getSimpleName().equals(TimeLineBean.class.getSimpleName())){
                    callback.onAPICallBack((TimeLineBean)o);
                }
            }
        };

        task.execute();
    }

    //TODO: args for create options
    public void createPlurk(final PlurkOAuthCallback callback,final String content, final String verb){
        AsyncTask task = new AsyncTask() {
            @Override
            protected JSONObject doInBackground(Object[] objects) {
                JSONObject jsonObject = null;
                try {
//                    Args args = new Args();
//                    JSONArray a = new JSONArray();
//                    a.put(3625668);//my user id
//                    args.add("limited_to",a.toString().trim());

//                    jsonObject = Util.getAuth(BaseActivity.this).using(Timeline.class).plurkAdd(content, Qualifier.fromString(verb), args);
                    jsonObject = Util.getAuth(BaseActivity.this).using(Timeline.class).plurkAdd(content, Qualifier.fromString(verb));
                }catch (Exception e){
                    e.printStackTrace();
                }

                return jsonObject;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);

                if(o != null && o.getClass().getSimpleName().equals(JSONObject.class.getSimpleName())){
                    try {
                        callback.onAPICallBack(parseCreateEditPlurkResult((JSONObject) o));
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(TAG,"create plurk failed");
                    }
                }
            }
        };

        task.execute();
    }

    public void editPlurk(final PlurkOAuthCallback callback,final String content, final long plurkId){
        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = Util.getAuth(BaseActivity.this).using(Timeline.class).plurkEdit(plurkId,content);
                }catch (Exception e){
                    e.printStackTrace();
                }

                return jsonObject;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);

                if(o != null && o.getClass().getSimpleName().equals(JSONObject.class.getSimpleName())){
                    try {
                        callback.onAPICallBack(parseCreateEditPlurkResult((JSONObject) o));
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(TAG,"edit plurk failed");
                    }
                }
            }
        };

        task.execute();
    }

    private BaseBean parseCreateEditPlurkResult(JSONObject jsonObject) throws Exception{
        BaseBean baseBean = null;
        if(jsonObject.has(StatusBean.KEY_ERROR_TEXT)){
            baseBean = StatusBean.parseStatusBean(jsonObject);
        }else{
            baseBean = PlurkBean.parseFullPlurkBean(jsonObject);
        }
        return baseBean;
    }
}
