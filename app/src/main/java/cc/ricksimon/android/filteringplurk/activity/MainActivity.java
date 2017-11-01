package cc.ricksimon.android.filteringplurk.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import cc.ricksimon.android.filteringplurk.R;
import cc.ricksimon.android.filteringplurk.data.UserInfo;
import cc.ricksimon.android.filteringplurk.utils.Log;
import cc.ricksimon.android.filteringplurk.utils.Util;

public class MainActivity extends BaseActivity {

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
                if(!UserInfo.userLoggedIn(MainActivity.this)) {
                    startActivity(new Intent(MainActivity.this, AuthorizeActivity.class));
                }else{

                }
            }
        };

        task.execute();
    }
}
