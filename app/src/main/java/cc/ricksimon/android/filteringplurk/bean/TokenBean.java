package cc.ricksimon.android.filteringplurk.bean;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import cc.ricksimon.android.filteringplurk.oauth.PlurkOAuthParameter;

/**
 * Created by Simon on 2017/11/7.
 */

public class TokenBean {

    private static final String KEY_APP_ID = "app_id";
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_ISSUED = "issued";
    private static final String KEY_DEVICE_ID = "deviceid";
    private static final String KEY_MODEL = "model";

    private String appId = null;
    private String userId = null;
    private String issued = null;
    private String deviceId = null;
    private String model = null;

    public String getAppId() {
        return appId;
    }
    public String getUserId() {
        return userId;
    }
    public String getIssued() {
        return issued;
    }
    public String getDeviceId() {
        return deviceId;
    }
    public String getModel() {
        return model;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public void setIssued(String issued) {
        this.issued = issued;
    }
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
    public void setModel(String model) {
        this.model = model;
    }

    private TokenBean(){
    }

    public static TokenBean parseTokenBean(JSONObject jsonObject) throws JSONException {
        TokenBean res = new TokenBean();

        if(jsonObject.has(KEY_APP_ID)){
            res.setAppId(jsonObject.getString(KEY_APP_ID));
        }
        if(jsonObject.has(KEY_USER_ID)){
            res.setUserId(jsonObject.getString(KEY_USER_ID));
        }
        if(jsonObject.has(KEY_ISSUED)){
            res.setIssued(jsonObject.getString(KEY_ISSUED));
        }
        if(jsonObject.has(KEY_DEVICE_ID)){
            res.setDeviceId(jsonObject.getString(KEY_DEVICE_ID));
        }
        if(jsonObject.has(KEY_MODEL)){
            res.setModel(jsonObject.getString(KEY_MODEL));
        }

        return res;
    }

    public boolean isValid(Context context){
        if(appId != null && !appId.isEmpty() && userId != null && !userId.isEmpty() && issued != null && !issued.isEmpty() && deviceId != null && !deviceId.isEmpty()){
            return (PlurkOAuthParameter.getUUID(context).trim().substring(0,32).equals(deviceId.trim()));
        }
        return false;
    }
}
