package cc.ricksimon.android.filteringplurk.utils;

import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Simon on 2018/2/19.
 */

public class ContentViewClient extends WebViewClient{

    public static final String TAG = ContentViewClient.class.getSimpleName();

    private static final String javaScriptStringGetSize = "javascript:getViewHeight.setSize(document.body.scrollHeight)";

    @Override
    public void onPageFinished(WebView view, String url) {
        view.loadUrl(javaScriptStringGetSize);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        //TODO: open url in other activity
        return true;
    }

    public static class GetWebViewHeightInterface{

        private OnSizeChangedListener onSizeChangedListener = null;
        private View view = null;

        public GetWebViewHeightInterface(View view, OnSizeChangedListener onSizeChangedListener){
            this.view = view;
            this.onSizeChangedListener = onSizeChangedListener;
        }

        @JavascriptInterface
        public void setSize(final float height){
            onSizeChangedListener.onSizeChanged(view, height);
        }
    }

    public interface OnSizeChangedListener{
        void onSizeChanged(View view, float sizeInPX);
    }
}