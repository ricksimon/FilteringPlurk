package com.google.jplurk_oauth.skeleton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import cc.ricksimon.android.filteringplurk.utils.Log;

public class RequestBuilder {
    private static final String TAG = RequestBuilder.class.getSimpleName();

    private PlurkOAuth auth;
    private String url;
    private HttpMethod method;
    private Args args;

    public RequestBuilder(PlurkOAuth plurkOAuth, String url) {
        this.auth = plurkOAuth;
        this.url = url;
    }

    public RequestBuilder with(Args args) {
        this.args = args;
        return this;
    }

    protected String result() throws RequestException, InterruptedException, ExecutionException, IOException {
    	validate();
    	String result = auth.sendRequest(url, args, method);
   		Log.d(TAG,String.format("Result: %s", result));
    	return result;
    }

    public boolean validate() {
        if(url != null && args != null && method != null)
            return true;
        throw new IllegalStateException("url, args, method should all have values.");
    }

    public RequestBuilder in(HttpMethod method) {
        this.method = method;
        return this;
    }

    public JSONObject thenJsonObject() throws RequestException {
        try {
            return new JSONObject(result());
        } catch (Exception e) {
            throw new RequestException(e);
        }
    }
    
    public JSONArray thenJsonArray() throws RequestException {
        try {
            return new JSONArray(result());
        } catch (Exception e) {
            throw new RequestException(e);
        }
    }
    
    public String thenStringObject() throws RequestException {
        try {
            return result();
        } catch (Exception e) {
            throw new RequestException(e);
        }
    }

    public RequestBuilder withoutArgs() {
        return with(new Args());
    }

}
