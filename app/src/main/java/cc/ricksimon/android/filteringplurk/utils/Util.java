package cc.ricksimon.android.filteringplurk.utils;

import android.content.Context;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth10aService;
import com.google.jplurk_oauth.skeleton.PlurkOAuth;

import cc.ricksimon.android.filteringplurk.oauth.PlurkOAuthApi;
import cc.ricksimon.android.filteringplurk.oauth.PlurkOAuthParameter;

/**
 * Created by Simon on 2017/11/1.
 */

public class Util {
    public static final long LANDING_PAGE_DELAY_TIME_MS = 1000;

    //OAuthService
    //always use Util.getService(); to get OAuthService
    private static OAuth10aService service = null;

    public static OAuth10aService getService(){
        if(service == null){
            if(Log.PRINT_LOG){
                service = new ServiceBuilder()
                        .apiKey(PlurkOAuthParameter.APP_KEY)
                        .apiSecret(PlurkOAuthParameter.APP_SECRET)
                        .callback(PlurkOAuthParameter.OAUTH_CALLBACK)
                        .debug()
                        .build(PlurkOAuthApi.instance());
            }else {
                service = new ServiceBuilder()
                        .apiKey(PlurkOAuthParameter.APP_KEY)
                        .apiSecret(PlurkOAuthParameter.APP_SECRET)
                        .callback(PlurkOAuthParameter.OAUTH_CALLBACK)
                        .build(PlurkOAuthApi.instance());
            }
        }

        return service;
    }

    //PlurkOAuth
    //always use Util.getAuth(); to get PlurkOAuth
    private static PlurkOAuth auth = null;

    public static PlurkOAuth getAuth(Context context){
        if(auth == null) {
            auth = new PlurkOAuth(context);
        }

        return auth;
    }

    public static void plurkOAuthTokenChanged(){
        service = null;
        auth = null;
        PlurkOAuth.clearCachedModule();
    }
}
