package cc.ricksimon.android.filteringplurk.activity;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.oauth.OAuth10aService;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import cc.ricksimon.android.filteringplurk.utils.Log;
import cc.ricksimon.android.filteringplurk.utils.PlurkOAuthApi;
import cc.ricksimon.android.filteringplurk.utils.PlurkOAuthParameter;

/**
 * Created by Simon on 2017/10/31.
 */

public class AuthorizeActivity extends AppCompatActivity {

    //OAuth Params
    private OAuth10aService service = null;
    private OAuth1RequestToken requestToken = null;
    private String verifier = null;


    private WebView webView = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initOAuth10aService();

        webView = new WebView(this);

        setupWebView();
        setContentView(webView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        getTokenAndLoadWebView();
    }

    private void initOAuth10aService(){
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

    private void setupWebView(){
        //enable javascript
        webView.getSettings().setJavaScriptEnabled(true);

        //setup listener
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageFinished(WebView view, String url) {
                if(url.startsWith(PlurkOAuthParameter.OAUTH_CALLBACK)){
                    parseVerifier(url);
                }

                if(verifier != null && !verifier.isEmpty()){
                    //TODO: continue authorization
                }
            }
        });
    }

    private void getTokenAndLoadWebView(){
        AsyncTask getReqTokenAndLoad = new AsyncTask() {
            @Override
            protected String doInBackground(Object[] params) {
                String url = "";
                try {
                    requestToken = service.getRequestToken();
                    url = service.getAuthorizationUrl(requestToken);
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

        getReqTokenAndLoad.execute();
    }

    private void parseVerifier(String url){
        Uri uri = Uri.parse(url);
        Set<String> args = uri.getQueryParameterNames();

        if(args.contains("oauth_verifier")){
            verifier = uri.getQueryParameter("oauth_verifier");
        }
    }
}
