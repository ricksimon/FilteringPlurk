package cc.ricksimon.android.filteringplurk.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Simon on 2018/2/18.
 */

public class ResponseContentBean extends BaseBean {

    @Override
    public void setBeanType() {
        setBeanType(ResponseContentBean.class.getSimpleName());
    }

    private static final String KEY_LANG = "lang";
    private static final String KEY_CONTENT_RAW = "content_raw";
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_QUALIFIER = "qualifier";
    private static final String KEY_PLURK_ID = "plurk_id";
    private static final String KEY_EDITABILITY = "editability";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_QUALIFIER_TRANSLATED = "qualifier_translated";
    private static final String KEY_WITH_RANDOM_EMOS = "with_random_emos";
    private static final String KEY_LAST_EDITED = "last_edited";
    private static final String KEY_ID = "id";
    private static final String KEY_POSTED = "posted";

    private String lang = null;
    private String contentRaw = null;
    private long userId = 0;
    private String qualifier = null;
    private long plurkId = 0;
    private int editability = 0;
    private String content = null;
    private String qualifierTranslated = null;
    private boolean withRandomEmos = false;
    private String lastEdited = null;
    private long id = 0;
    private String posted = null;

    public String getLang() {
        return lang;
    }
    public String getContentRaw() {
        return contentRaw;
    }
    public long getUserId() {
        return userId;
    }
    public String getQualifier() {
        return qualifier;
    }
    public long getPlurkId() {
        return plurkId;
    }
    public int getEditability() {
        return editability;
    }
    public String getContent() {
        return content;
    }
    public String getQualifierTranslated() {
        return qualifierTranslated;
    }
    public boolean isWithRandomEmos() {
        return withRandomEmos;
    }
    public String getLastEdited() {
        return lastEdited;
    }
    public long getId() {
        return id;
    }
    public String getPosted() {
        return posted;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
    public void setContentRaw(String contentRaw) {
        this.contentRaw = contentRaw;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }
    public void setPlurkId(long plurkId) {
        this.plurkId = plurkId;
    }
    public void setEditability(int editability) {
        this.editability = editability;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setQualifierTranslated(String qualifierTranslated) {
        this.qualifierTranslated = qualifierTranslated;
    }
    public void setWithRandomEmos(boolean withRandomEmos) {
        this.withRandomEmos = withRandomEmos;
    }
    public void setLastEdited(String lastEdited) {
        this.lastEdited = lastEdited;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setPosted(String posted) {
        this.posted = posted;
    }

    private ResponseContentBean(){
        setBeanType();
    }

    public static ResponseContentBean parseResponseContentBean(JSONObject jsonObject) throws JSONException{
        ResponseContentBean res = new ResponseContentBean();

        if(jsonObject.has(KEY_LANG)){
            res.setLang(jsonObject.getString(KEY_LANG));
        }
        if(jsonObject.has(KEY_CONTENT_RAW)){
            res.setContentRaw(jsonObject.getString(KEY_CONTENT_RAW));
        }
        if(jsonObject.has(KEY_USER_ID)){
            res.setUserId(jsonObject.getLong(KEY_USER_ID));
        }
        if(jsonObject.has(KEY_QUALIFIER)){
            res.setQualifier(jsonObject.getString(KEY_QUALIFIER));
        }
        if(jsonObject.has(KEY_PLURK_ID)){
            res.setPlurkId(jsonObject.getLong(KEY_PLURK_ID));
        }
        if(jsonObject.has(KEY_EDITABILITY)){
            res.setEditability(jsonObject.getInt(KEY_EDITABILITY));
        }
        if(jsonObject.has(KEY_CONTENT)){
            res.setContent(jsonObject.getString(KEY_CONTENT));
        }
        if(jsonObject.has(KEY_QUALIFIER_TRANSLATED)){
            res.setQualifierTranslated(jsonObject.getString(KEY_QUALIFIER_TRANSLATED));
        }
        if(jsonObject.has(KEY_WITH_RANDOM_EMOS)){
            res.setWithRandomEmos(jsonObject.getBoolean(KEY_WITH_RANDOM_EMOS));
        }
        if(jsonObject.has(KEY_LAST_EDITED)){
            res.setLastEdited(jsonObject.getString(KEY_LAST_EDITED));
        }
        if(jsonObject.has(KEY_ID)){
            res.setId(jsonObject.getLong(KEY_ID));
        }
        if(jsonObject.has(KEY_POSTED)){
            res.setPosted(jsonObject.getString(KEY_POSTED));
        }

        return res;
    }
}