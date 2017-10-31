package cc.ricksimon.android.filteringplurk.utils;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import cc.ricksimon.android.filteringplurk.data.UserInfo;

/**
 * Created by Simon on 2017/10/31.
 */

public class PlurkOAuthParameter {
    //for calling APIs
    public static final String APP_KEY = "";
    //for OAuth process
    public static final String APP_SECRET = "";
    public static final String OAUTH_CALLBACK = "";

    //OAuth paths
    public static final String PLURK_OAUTH_AUTHORIZATION_PATH = "https://www.plurk.com/m/authorize?oauth_token=%s";
    public static final String PLURK_OAUTH_REQUEST_TOKEN_PATH = "https://www.plurk.com/OAuth/request_token";
    public static final String PLURK_OAUTH_ACCESS_TOKEN_PATH = "https://www.plurk.com/OAuth/access_token";
}
