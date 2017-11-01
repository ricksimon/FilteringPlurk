package cc.ricksimon.android.filteringplurk.data;

import android.content.Context;
import android.content.SharedPreferences;

import cc.ricksimon.android.filteringplurk.utils.SharedPreferenceDefinition;

/**
 * Created by Simon on 2017/10/26.
 */

public class UserInfo {
    public static String getUserAccessToken(Context context){
        SharedPreferences s = context.getSharedPreferences(SharedPreferenceDefinition.SHARED_PREFERENCE_ROOT,Context.MODE_PRIVATE);

        return s.getString(SharedPreferenceDefinition.KEY_USER_ACCESS_TOKEN,null);
    }
    public static void setUserAccessToken(Context context, String token){
        SharedPreferences s = context.getSharedPreferences(SharedPreferenceDefinition.SHARED_PREFERENCE_ROOT,Context.MODE_PRIVATE);

        s.edit().putString(SharedPreferenceDefinition.KEY_USER_ACCESS_TOKEN,token).commit();
    }

    public static String getUserTokenSecret(Context context){
        SharedPreferences s = context.getSharedPreferences(SharedPreferenceDefinition.SHARED_PREFERENCE_ROOT,Context.MODE_PRIVATE);

        return s.getString(SharedPreferenceDefinition.KEY_USER_TOKEN_SECRET,null);
    }
    public static void setUserTokenSecret(Context context, String token){
        SharedPreferences s = context.getSharedPreferences(SharedPreferenceDefinition.SHARED_PREFERENCE_ROOT,Context.MODE_PRIVATE);

        s.edit().putString(SharedPreferenceDefinition.KEY_USER_TOKEN_SECRET,token).commit();
    }

    public static boolean userLoggedIn(Context context){
        String accessToken = getUserAccessToken(context);
        String tokenSecret = getUserTokenSecret(context);

        return (accessToken != null && !accessToken.isEmpty() && tokenSecret!= null && !tokenSecret.isEmpty());
    }

    public static void logOut(Context context){
        setUserAccessToken(context,null);
        setUserTokenSecret(context,null);
    }
}
