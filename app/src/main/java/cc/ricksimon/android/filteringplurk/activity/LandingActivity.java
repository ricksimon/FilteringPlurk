package cc.ricksimon.android.filteringplurk.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;

import com.google.jplurk_oauth.module.OAuthUtilities;

import org.json.JSONObject;

import cc.ricksimon.android.filteringplurk.R;
import cc.ricksimon.android.filteringplurk.bean.BaseBean;
import cc.ricksimon.android.filteringplurk.bean.TokenBean;
import cc.ricksimon.android.filteringplurk.bean.UserBean;
import cc.ricksimon.android.filteringplurk.oauth.PlurkOAuthCallback;
import cc.ricksimon.android.filteringplurk.oauth.PlurkOAuthUserInfo;
import cc.ricksimon.android.filteringplurk.utils.Log;
import cc.ricksimon.android.filteringplurk.utils.Util;

public class LandingActivity extends BaseActivity {

    public static final String TAG = LandingActivity.class.getSimpleName();

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
        CheckUserLoggedInTask task = new CheckUserLoggedInTask();

        task.execute();
    }

    private class CheckUserLoggedInTask extends AsyncTask{
        @Override
        protected Object doInBackground(Object[] params) {
            SystemClock.sleep(Util.LANDING_PAGE_DELAY_TIME_MS);

            if(!PlurkOAuthUserInfo.hasAccessToken(LandingActivity.this)){
                return null;
            }

            JSONObject checkToken = null;
            try {
                checkToken = Util.getAuth(LandingActivity.this).using(OAuthUtilities.class).checkToken();
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
                    if(TokenBean.parseTokenBean((JSONObject) o).isValid(LandingActivity.this)){
                        getUserProfile(new PlurkOAuthCallback() {
                            @Override
                            public void onAPICallBack(BaseBean dataBean) {
                                if(dataBean == null){
                                    Log.e(TAG,"getUserProfile-dataBean is null");
                                }else if(dataBean instanceof UserBean){
                                    Util.setUserProfile(LandingActivity.this,(UserBean) dataBean);
                                }

                                //TODO: go timeline page
//                                startActivity(new Intent( LandingActivity.this, ProfileActivity.class));
//                                startActivity(new Intent( LandingActivity.this, ListPlurkActivity.class));
                                startActivity(new Intent( LandingActivity.this, EditPlurkActivity.class));
                                finish();
                            }
                        });
                        return;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            //Exception / AccessToken not exists / return is not JSONObject
            startActivity(new Intent(LandingActivity.this, AuthorizeActivity.class));
        }
    }
}
