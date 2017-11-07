package cc.ricksimon.android.filteringplurk.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;

import com.google.jplurk_oauth.module.OAuthUtilities;
import com.google.jplurk_oauth.module.Users;
import com.google.jplurk_oauth.skeleton.PlurkOAuth;

import org.json.JSONObject;

import cc.ricksimon.android.filteringplurk.R;
import cc.ricksimon.android.filteringplurk.bean.TokenBean;
import cc.ricksimon.android.filteringplurk.oauth.PlurkOAuthUserInfo;
import cc.ricksimon.android.filteringplurk.utils.Log;
import cc.ricksimon.android.filteringplurk.utils.Util;

public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startCheckUserLoggedInTask();
    }

    private void startCheckUserLoggedInTask(){
        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                SystemClock.sleep(Util.LANDING_PAGE_DELAY_TIME_MS);

                if(!PlurkOAuthUserInfo.hasAccessToken(MainActivity.this)){
                    return null;
                }

                JSONObject checkToken = null;
                try {
                    checkToken = Util.getAuth(MainActivity.this).using(OAuthUtilities.class).checkToken();
                }catch(Exception e){
                    e.printStackTrace();
                    /*
                     * Failed message
                     * http status 400, body: {"error_text": "40106:invalid access token"}
                     *
                     */
                }

                return checkToken;
            }

            @Override
            protected void onPostExecute(Object o) {
                if(o != null && o.getClass().getSimpleName().equals(JSONObject.class.getSimpleName())){
                    try {
                        if(TokenBean.parseTokenBean((JSONObject) o).isValid(MainActivity.this)){
                            //TODO: go timeline page
//                            test();
                            return;
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                //Exception / AccessToken not exists / return is not JSONObject
                startActivity(new Intent(MainActivity.this, AuthorizeActivity.class));
            }
        };

        task.execute();
    }

    private void test(){
        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                PlurkOAuth auth = new PlurkOAuth(MainActivity.this);

                JSONObject jo = null;

                try{
                    jo = auth.using(Users.class).currUser();
                    Log.e(TAG,jo.toString());
                }catch(Exception e){
                    e.printStackTrace();
                }
                return null;
            }
        };

        task.execute();
    }
}
