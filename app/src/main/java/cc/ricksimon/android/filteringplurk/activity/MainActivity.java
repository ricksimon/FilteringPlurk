package cc.ricksimon.android.filteringplurk.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;

import com.google.jplurk_oauth.module.Users;
import com.google.jplurk_oauth.skeleton.PlurkOAuth;

import org.json.JSONObject;

import java.util.Iterator;

import cc.ricksimon.android.filteringplurk.R;
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
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                if(!PlurkOAuthUserInfo.userLoggedIn(MainActivity.this)) {
                    startActivity(new Intent(MainActivity.this, AuthorizeActivity.class));
                }else{
                    //TODO: go timeline page
                    test();
                }
            }
        };

        task.execute();
    }

    private void test(){
        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                PlurkOAuth auth = new PlurkOAuth(MainActivity.this);
                try{
                    JSONObject jo = auth.using(Users.class).currUser();
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
