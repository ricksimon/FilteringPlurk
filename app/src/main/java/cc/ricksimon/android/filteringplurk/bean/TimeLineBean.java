package cc.ricksimon.android.filteringplurk.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Simon on 2018/2/16.
 */

public class TimeLineBean extends BaseBean {

    @Override
    public void setBeanType() {
        setBeanType(TimeLineBean.class.getSimpleName());
    }

    private static final String KEY_USERS = "plurk_users";
    private static final String KEY_PLURKS = "plurks";

    private ArrayList<PlurkBean> plurks = null;
    private HashMap<String, UserBean> users = null;

    public ArrayList<PlurkBean> getPlurks() {
        return plurks;
    }
    public HashMap<String, UserBean> getUsers() {
        return users;
    }

    public void setPlurks(ArrayList<PlurkBean> plurks) {
        this.plurks = plurks;
    }
    public void setUsers(HashMap<String, UserBean> users) {
        this.users = users;
    }

    private TimeLineBean(){
        setBeanType();
    }

    public static TimeLineBean parseTimeLineBean(JSONObject jsonObject) throws JSONException{
        TimeLineBean res = new TimeLineBean();

        HashMap<String,UserBean> users = new HashMap<String,UserBean>();
        ArrayList<PlurkBean> plurks = new ArrayList<PlurkBean>();

        if(jsonObject.has(KEY_USERS)){
            JSONObject rawUsers = jsonObject.getJSONObject(KEY_USERS);
            Iterator<String> keys = rawUsers.keys();
            while(keys.hasNext()){
                String key = keys.next();
                UserBean user = UserBean.parseFullUserBean(rawUsers.getJSONObject(key));

                users.put(key,user);
            }
        }

        if(jsonObject.has(KEY_PLURKS)){
            JSONArray rawPlurks = jsonObject.getJSONArray(KEY_PLURKS);
            for(int i=0;i<rawPlurks.length();i++){
                PlurkBean plurk = PlurkBean.parseFullPlurkBean(rawPlurks.getJSONObject(i));

                plurks.add(plurk);
            }
        }

        if(users.size() !=0 && plurks.size() != 0){
            res.setUsers(users);
            res.setPlurks(plurks);
        }

        return res;
    }
}
