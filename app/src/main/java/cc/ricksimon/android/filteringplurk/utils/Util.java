package cc.ricksimon.android.filteringplurk.utils;

import android.content.Context;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth10aService;
import com.google.jplurk_oauth.skeleton.PlurkOAuth;

import cc.ricksimon.android.filteringplurk.bean.UserBean;
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


    //Logic for get avatar URL
    public static final int TYPE_SMALL = 0;
    public static final int TYPE_MEDIUM = 1;
    public static final int TYPE_BIG = 2;

    private static final String AVATAR_URL_BASE = "https://avatars.plurk.com/";
    private static final String AVATAR_PATH_DEFAULT_AVATAR = "static/default_";
    private static final String AVATAR_PATH_SMALL = "small";
    private static final String AVATAR_PATH_MEDIUM = "medium";
    private static final String AVATAR_PATH_BIG = "big";
    private static final String AVATAR_PATH_GIF = ".gif";
    private static final String AVATAR_PATH_JPG = ".jpg";

    public static String getAvatarUrl(int type, UserBean userBean){
        String avatarUrl = null;

        switch(type){
            case TYPE_BIG:
                if(userBean.getAvatarBig() != null && !userBean.getAvatarBig().isEmpty()){
                    avatarUrl = userBean.getAvatarBig();
                }
                break;
            case TYPE_MEDIUM:
                if(userBean.getAvatarMedium() != null && !userBean.getAvatarMedium().isEmpty()){
                    avatarUrl = userBean.getAvatarMedium();
                }
                break;
            case TYPE_SMALL:
            default:
                if(userBean.getAvatarSmall() != null && !userBean.getAvatarSmall().isEmpty()){
                    avatarUrl = userBean.getAvatarSmall();
                }
                break;
        }

        if(avatarUrl == null || avatarUrl.isEmpty()){
            avatarUrl = generateAvatarURL(type, userBean);
        }

        return avatarUrl;
    }
    private static String generateAvatarURL(int type, UserBean userBean){
        String avatarUrl = null;
        String argSize = null;
        String argType = null;

        switch(type){
            case TYPE_BIG:
                argSize = AVATAR_PATH_BIG;
                argType = AVATAR_PATH_JPG;
                break;
            case TYPE_MEDIUM:
                argSize = AVATAR_PATH_MEDIUM;
                argType = AVATAR_PATH_GIF;
                break;
            case TYPE_SMALL:
            default:
                argSize = AVATAR_PATH_SMALL;
                argType = AVATAR_PATH_GIF;
                break;
        }

        if(userBean.getHasProfileImage() == 1 && userBean.getAvatar() == 0){
            avatarUrl = AVATAR_URL_BASE+userBean.getId()+"-"+argSize+argType;
        }else if(userBean.getHasProfileImage() == 1 && userBean.getAvatar() != 0){
            avatarUrl = AVATAR_URL_BASE+userBean.getId()+"-"+argSize+userBean.getAvatar()+argType;
        }else{
            avatarUrl = AVATAR_URL_BASE+AVATAR_PATH_DEFAULT_AVATAR+argSize+AVATAR_PATH_GIF;
        }

        return avatarUrl;
    }
}
