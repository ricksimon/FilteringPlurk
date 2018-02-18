package cc.ricksimon.android.filteringplurk.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.oauth.OAuth10aService;
import com.google.gson.Gson;
import com.google.jplurk_oauth.skeleton.PlurkOAuth;

import cc.ricksimon.android.filteringplurk.bean.FriendBean;
import cc.ricksimon.android.filteringplurk.bean.UserBean;
import cc.ricksimon.android.filteringplurk.oauth.PlurkOAuthApi;
import cc.ricksimon.android.filteringplurk.oauth.PlurkOAuthParameter;
import cc.ricksimon.android.filteringplurk.oauth.PlurkOAuthUserInfo;

/**
 * Created by Simon on 2017/11/1.
 */

public class Util {

    public static final String TAG = Util.class.getSimpleName();

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

    //Show OAuth Token/Secret, only enable when develop
    private static boolean showToken = false;
    public static void showTokens(Context context){
        if(!showToken){
            return;
        }
        if(PlurkOAuthUserInfo.hasAccessToken(context)) {
            OAuth1AccessToken t = PlurkOAuthUserInfo.getAccessToken(context);
            Log.e(TAG,"token:"+t.getToken());
            Log.e(TAG,"secret:"+t.getTokenSecret());
        }
    }

    //Set/Get User Basic Profile
    public static void setUserProfile(Context context, UserBean userBean){
        SharedPreferences s = context.getSharedPreferences(SharedPreferenceDefinition.SHARED_PREFERENCE_ROOT,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = s.edit();

        Gson gson = new Gson();
        String jsonString = gson.toJson(userBean);
        editor.putString(SharedPreferenceDefinition.KEY_USER_PROFILE,jsonString);

        editor.commit();
    }
    public static UserBean getUserProfile(Context context){
        SharedPreferences s = context.getSharedPreferences(SharedPreferenceDefinition.SHARED_PREFERENCE_ROOT,Context.MODE_PRIVATE);

        String jsonString = s.getString(SharedPreferenceDefinition.KEY_USER_PROFILE,null);
        if(jsonString == null){
            return null;
        }

        Gson gson = new Gson();
        UserBean userBean = gson.fromJson(jsonString,UserBean.class);

        return userBean;
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
            avatarUrl = generateAvatarURL(type, userBean.getId(), userBean.getHasProfileImage(), userBean.getAvatar());
        }

        return avatarUrl;
    }
    public static String getAvatarUrl(int type, FriendBean friendBean){
        return generateAvatarURL(type, friendBean.getId(), friendBean.getHasProfileImage(), friendBean.getAvatar());
    }
    private static String generateAvatarURL(int type, long id, int hasProfileImage, int avatar){
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

        if(hasProfileImage == 1 && avatar == 0){
            avatarUrl = AVATAR_URL_BASE+id+"-"+argSize+argType;
        }else if(hasProfileImage == 1 && avatar != 0){
            avatarUrl = AVATAR_URL_BASE+id+"-"+argSize+avatar+argType;
        }else{
            avatarUrl = AVATAR_URL_BASE+AVATAR_PATH_DEFAULT_AVATAR+argSize+AVATAR_PATH_GIF;
        }

        return avatarUrl;
    }
}
