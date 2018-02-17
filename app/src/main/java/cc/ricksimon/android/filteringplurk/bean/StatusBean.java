package cc.ricksimon.android.filteringplurk.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Simon on 2017/11/7.
 */

public class StatusBean extends BaseBean{

    @Override
    public void setBeanType() {
        setBeanType(StatusBean.class.getSimpleName());
    }

    public static final String KEY_ERROR_TEXT = "error_text";

    private String message = null;
    private boolean isSuccess = false;

    public String getMessage() {
        return message;
    }
    public boolean isSuccess() {
        return isSuccess;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    private StatusBean(){
        setBeanType();
    }

    public static StatusBean parseStatusBean(JSONObject jsonObject) throws JSONException{
        StatusBean res = new StatusBean();

        if(jsonObject.has(KEY_ERROR_TEXT)){
            res.setMessage(jsonObject.getString(KEY_ERROR_TEXT));
            res.setSuccess(false);
        }

        return res;
    }
}