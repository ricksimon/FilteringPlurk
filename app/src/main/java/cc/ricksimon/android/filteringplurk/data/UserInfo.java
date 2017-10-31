package cc.ricksimon.android.filteringplurk.data;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Simon on 2017/10/26.
 */

public class UserInfo {
    //shared preference args
    private static final String SHARED_PREFERENCE_ROOT = "cc.ricksimon.android.filteringplurk.SHAREDPREFERENCE";
    private static final String KEY_USER_ACCESS_TOKEN = "userAccessToken";
    private static final String KEY_USER_TOKEN_SECRET = "userTokenSecret";

    public static String getUserAccessToken(Context context){
        SharedPreferences s = context.getSharedPreferences(SHARED_PREFERENCE_ROOT,Context.MODE_PRIVATE);

        return s.getString(KEY_USER_ACCESS_TOKEN,null);
    }
    public static void setUserAccessToken(Context context, String token){
        SharedPreferences s = context.getSharedPreferences(SHARED_PREFERENCE_ROOT,Context.MODE_PRIVATE);

        s.edit().putString(KEY_USER_ACCESS_TOKEN,token).commit();
    }
    public static String getUserTokenSecret(Context context){
        SharedPreferences s = context.getSharedPreferences(SHARED_PREFERENCE_ROOT,Context.MODE_PRIVATE);

        return s.getString(KEY_USER_TOKEN_SECRET,null);
    }
    public static void setUserTokenSecret(Context context, String token){
        SharedPreferences s = context.getSharedPreferences(SHARED_PREFERENCE_ROOT,Context.MODE_PRIVATE);

        s.edit().putString(KEY_USER_TOKEN_SECRET,token).commit();
    }
}
