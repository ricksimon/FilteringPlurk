package cc.ricksimon.android.filteringplurk.activity;

import android.support.v7.app.AppCompatActivity;

import com.google.jplurk_oauth.skeleton.PlurkOAuth;

import cc.ricksimon.android.filteringplurk.data.PlurkConfig;

/**
 * Created by Simon on 2017/10/26.
 */

public class BaseActivity extends AppCompatActivity {
    private static PlurkOAuth auth = null;

    public PlurkOAuth getPlurkOAuth(){
        if(auth != null){
            return auth;
        }

        String accessToken = PlurkConfig.getUserAccessToken(this);
        String tokenSecret = PlurkConfig.getUserTokenSecret(this);

        if(accessToken == null || accessToken.isEmpty() ||
                tokenSecret == null || tokenSecret.isEmpty()){
            //TODO: call OAuth login page
        }

        auth = new PlurkOAuth(PlurkConfig.APIKey,PlurkConfig.APISecret,accessToken,tokenSecret);

        return auth;
    }
}
