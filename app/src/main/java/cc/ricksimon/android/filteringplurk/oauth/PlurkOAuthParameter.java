package cc.ricksimon.android.filteringplurk.oauth;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.UUID;

import cc.ricksimon.android.filteringplurk.utils.SharedPreferenceDefinition;

/**
 * Created by Simon on 2017/10/31.
 */

public class PlurkOAuthParameter {
    //for calling APIs
    public static final String APP_KEY = "";
    //for OAuth process
    public static final String APP_SECRET = "";
    public static final String OAUTH_CALLBACK = "";

    //OAuth
    public static final String PLURK_LOGIN_PAGE = "https://www.plurk.com/m/login";
    public static final String PLURK_OAUTH_AUTHORIZATION_PATH_ROOT = "https://www.plurk.com/m/authorize";

    public static final String PLURK_OAUTH_SIMPLE_AUTH_PATH = PLURK_OAUTH_AUTHORIZATION_PATH_ROOT + "?oauth_token=%s";
    public static final String PLURK_OAUTH_FULL_AUTH_PATH   = PLURK_OAUTH_AUTHORIZATION_PATH_ROOT + "?oauth_token=%s&deviceid=%s&model=%s";

    public static final String PLURK_OAUTH_REQUEST_TOKEN_PATH = "https://www.plurk.com/OAuth/request_token";
    public static final String PLURK_OAUTH_ACCESS_TOKEN_PATH = "https://www.plurk.com/OAuth/access_token";

    public static String getUUID(Context context){
        SharedPreferences s = context.getSharedPreferences(SharedPreferenceDefinition.SHARED_PREFERENCE_ROOT,Context.MODE_PRIVATE);

        String uuid = s.getString(SharedPreferenceDefinition.KEY_UUID,null);
        if(uuid == null || uuid.isEmpty()){
            uuid = UUID.randomUUID().toString();
            s.edit().putString(SharedPreferenceDefinition.KEY_UUID,uuid).commit();
        }

        return uuid;
    }
}
