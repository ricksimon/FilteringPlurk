package cc.ricksimon.android.filteringplurk.utils;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth10aService;

import cc.ricksimon.android.filteringplurk.oauth.PlurkOAuthApi;
import cc.ricksimon.android.filteringplurk.oauth.PlurkOAuthParameter;

/**
 * Created by Simon on 2017/11/1.
 */

public class Util {
    public static final long LANDING_PAGE_DELAY_TIME_MS = 1000;
    //OAuth Service
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
}
