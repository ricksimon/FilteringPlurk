package cc.ricksimon.android.filteringplurk.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;

import com.google.jplurk_oauth.module.Timeline;
import com.google.jplurk_oauth.module.Users;
import com.google.jplurk_oauth.skeleton.Args;
import com.google.jplurk_oauth.skeleton.RequestException;

import org.json.JSONObject;

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

    public void getUserProfile(final PlurkOAuthCallback callback){
        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
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
            protected Object doInBackground(Object[] objects) {
                TimeLineBean timeLineBean = null;
                try {
//                    Args args = new Args();
//                    args.add("filter","my");
//                    JSONObject jsonObject = Util.getAuth(BaseActivity.this).using(Timeline.class).getPlurks(args);
                    JSONObject jsonObject = Util.getAuth(BaseActivity.this).using(Timeline.class).getPlurks();
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
}
