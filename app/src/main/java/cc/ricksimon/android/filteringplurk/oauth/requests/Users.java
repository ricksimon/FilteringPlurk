package cc.ricksimon.android.filteringplurk.oauth.requests;

import android.content.Context;

import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import cc.ricksimon.android.filteringplurk.oauth.PlurkOAuthParameter;
import cc.ricksimon.android.filteringplurk.oauth.PlurkOAuthUserInfo;
import cc.ricksimon.android.filteringplurk.utils.Util;

/**
 * Created by Simon on 2017/11/1.
 */

public class Users {
    private static final String USER_ROOT = PlurkOAuthParameter.PLURK_API_ROOT + "/Users";

    private static final String ME = USER_ROOT + "/me";
    private static final String UPDATE = USER_ROOT + "/update";
    private static final String UPDATE_AVATAR = USER_ROOT + "/updateAvatar";
    private static final String GET_KARMA_STATS = USER_ROOT + "/getKarmaStats";

    public static Response getUserProfile(Context context) throws InterruptedException, ExecutionException, IOException {
        OAuthRequest request = new OAuthRequest(Verb.GET,ME);
        return signAndExecute(context, request);
    }

    public static Response updateUserProfile(){
        // FIXME: 2017/11/1
        return null;
    }

    public static Response updateAvatar(){
        // FIXME: 2017/11/1
        return null;
    }

    public static Response getKarmaStats(Context context) throws InterruptedException, ExecutionException, IOException {
        OAuthRequest request = new OAuthRequest(Verb.GET,GET_KARMA_STATS);
        return signAndExecute(context,request);
    }


    private static Response signAndExecute(Context context, OAuthRequest request) throws InterruptedException, ExecutionException, IOException {
        Util.getService().signRequest(PlurkOAuthUserInfo.getAccessToken(context),request);
        return Util.getService().execute(request);
    }
}
