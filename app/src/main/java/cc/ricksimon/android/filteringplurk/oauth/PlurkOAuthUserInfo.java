package cc.ricksimon.android.filteringplurk.oauth;

import android.content.Context;
import android.content.SharedPreferences;

import com.github.scribejava.core.model.OAuth1AccessToken;

import cc.ricksimon.android.filteringplurk.utils.SharedPreferenceDefinition;

/**
 * Created by Simon on 2017/10/26.
 */

public class PlurkOAuthUserInfo {

    public static OAuth1AccessToken getAccessToken(Context context){
        SharedPreferences s = context.getSharedPreferences(SharedPreferenceDefinition.SHARED_PREFERENCE_ROOT,Context.MODE_PRIVATE);

        String token = s.getString(SharedPreferenceDefinition.KEY_USER_ACCESS_TOKEN,null);
        String secret = s.getString(SharedPreferenceDefinition.KEY_USER_TOKEN_SECRET,null);

        return (token != null && !token.isEmpty() && secret != null && !secret.isEmpty())? new OAuth1AccessToken(token, secret) : null ;
    }
    public static void setAccessToken(Context context, OAuth1AccessToken token){
        if(token == null){
            setAccessToken(context, null, null);
        }else {
            setAccessToken(context, token.getToken(), token.getTokenSecret());
        }
    }
    private static void setAccessToken(Context context, String token, String secret){
        SharedPreferences s = context.getSharedPreferences(SharedPreferenceDefinition.SHARED_PREFERENCE_ROOT,Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = s.edit();

        editor.putString(SharedPreferenceDefinition.KEY_USER_ACCESS_TOKEN,token);
        editor.putString(SharedPreferenceDefinition.KEY_USER_TOKEN_SECRET,secret);

        editor.commit();
    }

    public static boolean userLoggedIn(Context context){
        OAuth1AccessToken token = getAccessToken(context);

        String accessToken = token.getToken();
        String tokenSecret = token.getTokenSecret();

        return (accessToken != null && !accessToken.isEmpty() && tokenSecret!= null && !tokenSecret.isEmpty());
    }

    public static void clearAccessToken(Context context){
        setAccessToken(context,null);
    }
}
