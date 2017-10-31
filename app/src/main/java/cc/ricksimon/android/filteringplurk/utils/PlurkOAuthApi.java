package cc.ricksimon.android.filteringplurk.utils;

import com.github.scribejava.core.builder.api.DefaultApi10a;
import com.github.scribejava.core.model.OAuth1RequestToken;

/**
 * Created by Simon on 2017/10/31.
 */

public class PlurkOAuthApi extends DefaultApi10a {

    protected PlurkOAuthApi() {
    }

    private static class InstanceHolder {
        private static final PlurkOAuthApi INSTANCE = new PlurkOAuthApi();
    }

    public static PlurkOAuthApi instance(){
        return InstanceHolder.INSTANCE;
    }

    @Override
    public String getRequestTokenEndpoint() {
        return PlurkOAuthParameter.PLURK_OAUTH_REQUEST_TOKEN_PATH;
    }

    @Override
    public String getAccessTokenEndpoint() {
        return PlurkOAuthParameter.PLURK_OAUTH_ACCESS_TOKEN_PATH;
    }

    @Override
    public String getAuthorizationUrl(OAuth1RequestToken requestToken) {
        return String.format(PlurkOAuthParameter.PLURK_OAUTH_AUTHORIZATION_PATH, requestToken.getToken());
    }
}
