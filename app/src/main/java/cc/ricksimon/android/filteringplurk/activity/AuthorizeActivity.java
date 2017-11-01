package cc.ricksimon.android.filteringplurk.activity;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.jaredrummler.android.device.DeviceName;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import cc.ricksimon.android.filteringplurk.R;
import cc.ricksimon.android.filteringplurk.oauth.PlurkOAuthUserInfo;
import cc.ricksimon.android.filteringplurk.oauth.PlurkOAuthApi;
import cc.ricksimon.android.filteringplurk.oauth.PlurkOAuthParameter;
import cc.ricksimon.android.filteringplurk.utils.Util;

/**
 * Created by Simon on 2017/10/31.
 */

public class AuthorizeActivity extends BaseActivity {

    //OAuth Params
    private OAuth1RequestToken requestToken = null;
    private String verifier = null;

    //device info
    private DeviceName.DeviceInfo deviceInfo = null;

    private AuthorizeActivity mActivity = null;
    private WebView webView = null;

    private static final boolean withDetailInfo = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivity = this;

        initial();
        setUpView();
    }

    @Override
    protected void onResume() {
        super.onResume();

        startGetRequestTokenAndLoadWebViewTask();
    }

    private void initial(){
        initWebView();
        getDeviceInfoInBackground();
    }

    private void setUpView(){
        webView.setVisibility(View.INVISIBLE);
        setContentView(R.layout.progress_dialog);
    }

    private void initWebView(){
        webView = new WebView(this);

        //enable javascript
        webView.getSettings().setJavaScriptEnabled(true);

        //setup listener
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageFinished(WebView view, String url) {
                if(url.startsWith(PlurkOAuthParameter.PLURK_LOGIN_PAGE)){
                    if(webView.getVisibility() != View.VISIBLE) {
                        webView.setVisibility(View.VISIBLE);
                        setContentView(webView);
                    }
                    return;
                }
                if(url.startsWith(PlurkOAuthParameter.PLURK_OAUTH_AUTHORIZATION_PATH_ROOT)){
                    if(webView.getVisibility() != View.VISIBLE) {
                        webView.setVisibility(View.VISIBLE);
                        setContentView(webView);
                    }
                    return;
                }

                if(url.startsWith(PlurkOAuthParameter.OAUTH_CALLBACK)){
                    parseVerifier(url);

                    if(verifier != null && !verifier.isEmpty()){
                        webView.setVisibility(View.INVISIBLE);
                        setContentView(R.layout.progress_dialog);

                        startGetAccessTokenTask();

                        webView.clearCache(true);
                        //clear cookies
                        CookieManager.getInstance().removeSessionCookies(null);
                    }
                }
            }
        });
    }

    private void getDeviceInfoInBackground(){
        DeviceName.with(mActivity).request(new DeviceName.Callback() {
            @Override
            public void onFinished(DeviceName.DeviceInfo info, Exception error) {
                deviceInfo = info;
            }
        });
    }

    private void startGetRequestTokenAndLoadWebViewTask(){
        AsyncTask task = new AsyncTask() {
            @Override
            protected String doInBackground(Object[] params) {
                String url = "";
                try {
                    requestToken = Util.getService().getRequestToken();
                    url = obtainAuthURL();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                return url;
            }

            @Override
            protected void onPostExecute(Object o) {
                if(o.getClass().getSimpleName().equals("String")){
                    webView.loadUrl((String)o);
                }
            }
        };

        task.execute();
    }

    private void startGetAccessTokenTask(){
        AsyncTask task = new AsyncTask() {
            private OAuth1AccessToken accessToken = null;

            @Override
            protected Object doInBackground(Object[] params) {
                try {
                    accessToken = Util.getService().getAccessToken(requestToken, verifier);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                if(accessToken != null) {
                    PlurkOAuthUserInfo.setAccessToken(mActivity, accessToken);
                    finish();
                }
            }
        };

        task.execute();
    }

    private void parseVerifier(String url){
        Uri uri = Uri.parse(url);
        Set<String> args = uri.getQueryParameterNames();

        if(args.contains("oauth_verifier")){
            verifier = uri.getQueryParameter("oauth_verifier");
        }
    }

    private String obtainAuthURL(){
        if(withDetailInfo){
            return PlurkOAuthApi.getAuthorizationUrlWithDeviceIdAndModel(requestToken, PlurkOAuthParameter.getUUID(mActivity), obtainDeviceName());
        }else{
            return Util.getService().getAuthorizationUrl(requestToken);
        }
    }

    private String obtainDeviceName(){
        String deviceName = DeviceName.getDeviceName();
        if(deviceInfo != null){
            deviceName = deviceInfo.manufacturer + " " + deviceInfo.marketName + " (" + deviceInfo.model + ")";
        }
        deviceName = deviceName.replace(" ", "+");

        return deviceName;
    }
}
