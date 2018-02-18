package cc.ricksimon.android.filteringplurk.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Simon on 2018/2/18.
 */

public class ResponseBean extends BaseBean{

    @Override
    public void setBeanType() {
        setBeanType(ResponseBean.class.getSimpleName());
    }

    private static final String KEY_FRIENDS = "friends";
    private static final String KEY_RESPONSE_COUNT = "response_count";
    private static final String KEY_RESPONSES_SEEN = "responses_seen";
    private static final String KEY_RESPONSES = "responses";

    private HashMap<String,FriendBean> friends = null;
    private int responseCount = -1;
    private int responsesSeen = -1;
    private ArrayList<ResponseContentBean> responses = null;

    public HashMap<String, FriendBean> getFriends() {
        return friends;
    }
    public int getResponseCount() {
        return responseCount;
    }
    public int getResponsesSeen() {
        return responsesSeen;
    }
    public ArrayList<ResponseContentBean> getResponses() {
        return responses;
    }
    private ResponseBean(){
        setBeanType();
    }

    public void setFriends(HashMap<String, FriendBean> friends) {
        this.friends = friends;
    }
    public void setResponseCount(int responseCount) {
        this.responseCount = responseCount;
    }
    public void setResponsesSeen(int responsesSeen) {
        this.responsesSeen = responsesSeen;
    }
    public void setResponses(ArrayList<ResponseContentBean> responses) {
        this.responses = responses;
    }

    public static ResponseBean parseResponseBean(JSONObject jsonObject) throws JSONException{
        ResponseBean res = new ResponseBean();

        HashMap<String,FriendBean> friends = new HashMap<String,FriendBean>();
        ArrayList<ResponseContentBean> responses = new ArrayList<ResponseContentBean>();

        if(jsonObject.has(KEY_RESPONSE_COUNT)){
            res.setResponseCount(jsonObject.getInt(KEY_RESPONSE_COUNT));
        }
        if(jsonObject.has(KEY_RESPONSES_SEEN)){
            res.setResponsesSeen(jsonObject.getInt(KEY_RESPONSES_SEEN));
        }

        if(jsonObject.has(KEY_FRIENDS)){
            JSONObject rawFriends = jsonObject.getJSONObject(KEY_FRIENDS);
            Iterator<String> keys = rawFriends.keys();
            while(keys.hasNext()){
                String key = keys.next();
                FriendBean friend = FriendBean.parseFriendBean(rawFriends.getJSONObject(key));

                friends.put(key,friend);
            }
        }

        if(jsonObject.has(KEY_RESPONSES)){
            JSONArray rawResponses = jsonObject.getJSONArray(KEY_RESPONSES);
            for(int i=0;i<rawResponses.length();i++){
                ResponseContentBean response = ResponseContentBean.parseResponseContentBean(rawResponses.getJSONObject(i));

                responses.add(response);
            }
        }

        if(friends.size() != 0 && responses.size() != 0){
            res.setFriends(friends);
            res.setResponses(responses);
        }

        return res;
    }
}