package cc.ricksimon.android.filteringplurk.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Simon on 2017/11/3.
 */

/*
 * plurk_id: The unique Plurk id, used for identification of the plurk.
 * qualifier: The English qualifier, can be "says", show all: Show example data
 * qualifier_translated: Only set if the language is not English, will be the translated qualifier. Can be "siger" if plurk.lang is "da" (Danish).
 * is_unread: Specifies if the plurk is read, unread or muted. Show example data
 * plurk_type: Specifies what type of plurk it is and if the plurk has been responded by the user. The value of plurk_type is only correct when calling getPlurks with "responded" filter (this is done for perfomance and caching reasons).
 * user_id: Which timeline does this Plurk belong to.
 * owner_id: Who is the owner/poster of this plurk. For anonymous plurk, this will be overrided by user "anonymous" (uid: 99999).
 * posted: The date this plurk was posted.
 * no_comments: If set to 1, then responses are disabled for this plurk. If set to 2, then only friends can respond to this plurk.
 * content: The formatted content, emoticons and images will be turned into IMG tags etc.
 * content_raw: The raw content as user entered it, useful when editing plurks or if you want to format the content differently.
 * response_count: How many responses does the plurk have.
 * responses_seen: How many of the responses have the user read. This is automatically updated when fetching responses or marking a plurk as read.
 * limited_to: If the Plurk is public limited_to is null. If the Plurk is posted to a user's friends then limited_to is [0]. If limited_to is [1,2,6,3] then it's posted only to these user ids.
 * favorite: True if current user has liked given plurk.
 * favorite_count: Number of users who liked given plurk.
 * favorers: List of ids of users who liked given plurk (can be truncated).
 * replurkable: True if plurk can be replurked.
 * replurked: True if plurk has been replurked by current user.
 * replurker_id: ID of a user who has replurked given plurk to current user's timeline.
 * replurkers_count: Number of users who replurked given plurk.
 * replurkers: List of ids of users who replurked given plurk (can be truncated).
 */

/* example
 * {
 * "replurked":false,
 * "porn":false,
 * "mentioned":0,
 * "replurkable":true,
 * "favorite_count":0,
 * "is_unread":0,
 * "favorers":[],
 * "user_id":LONG,
 * "plurk_type":0,
 * "qualifier_translated":"",
 * "bookmark":false,
 * "coins":0,
 * "content":STRING,
 * "replurker_id":null,
 * "excluded":null,
 * "has_gift":false,
 * "owner_id":LONG,
 * "responses_seen":0,
 * "qualifier":":",
 * "plurk_id":LONG,
 * "response_count":1,
 * "replurkers_count":0,
 * "anonymous":false,
 * "last_edited":null,
 * "limited_to":null,
 * "no_comments":0,
 * "posted":STRING,
 * "lang":"tr_ch",
 * "content_raw":STRING,
 * "replurkers":[],
 * "favorite":false,
 * "responded":0
 * }
 */

public class PlurkBean extends BaseBean {

    @Override
    public void setBeanType() {
        setBeanType(PlurkBean.class.getSimpleName());
    }

    public enum IsUnread{
        READ(0),
        UNREAD(1),
        MUTED(2);

        private int isUnread;
        IsUnread(int isUnread){
            this.isUnread = isUnread;
        }
    }

    public enum NoComments{
        ALL(0),
        DISABLED(1),
        ONLY_FRIENDS(2);

        private int noComment;
        NoComments(int noComment){
            this.noComment = noComment;
        }
    }

    private static final String KEY_PLURK_ID = "plurk_id";
    private static final String KEY_QUALIFIER = "qualifier";
    private static final String KEY_QUALIFIER_TRANSLATED = "qualifier_translated";
    private static final String KEY_IS_UNREAD = "is_unread";
    private static final String KEY_PLURK_TYPE = "plurk_type";
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_OWNER_ID = "owner_id";
    private static final String KEY_POSTED = "posted";
    private static final String KEY_NO_COMMENTS = "no_comments";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_CONTENT_RAW = "content_raw";
    private static final String KEY_RESPONSE_COUNT = "response_count";
    private static final String KEY_RESPONSES_SEEN = "responses_seen";
    private static final String KEY_LIMITED_TO = "limited_to";
    private static final String KEY_FAVORITE = "favorite";
    private static final String KEY_FAVORITE_COUNT = "favorite_count";
    private static final String KEY_FAVORERS = "favorers";
    private static final String KEY_REPLURKABLE = "replurkable";
    private static final String KEY_REPLURKED = "replurked";
    private static final String KEY_REPLURKER_ID = "replurker_id";
    private static final String KEY_REPLURKERS_COUNT = "replurkers_count";
    private static final String KEY_REPLURKERS = "replurkers";

    private long plurkId = 0;
    private String qualifier = null;
    private String qualifierTranslated = null;
    private int isUnread = 0;
    private int plurkType = 0;
    private long userId = 0;
    private long ownerId = 0;
    private String posted = null;
    private int noComments = 0;
    private String content = null;
    private String contentRaw = null;
    private int responseCount = 0;
    private int responsesSeen = 0;
    private String limitedTo = null;
    private boolean favorite = false;
    private int favoriteCount = 0;
    private String favorers = null;
    private boolean replurkable = false;
    private boolean replurked = false;
    private String replurkerId = null;
    private int replurkersCount = 0;
    private String replurkers = null;

    public long getPlurkId() {
        return plurkId;
    }
    public String getQualifier() {
        return qualifier;
    }
    public String getQualifierTranslated() {
        return qualifierTranslated;
    }
    public int getIsUnread() {
        return isUnread;
    }
    public int getPlurkType() {
        return plurkType;
    }
    public long getUserId() {
        return userId;
    }
    public long getOwnerId() {
        return ownerId;
    }
    public String getPosted() {
        return posted;
    }
    public int getNoComments() {
        return noComments;
    }
    public String getContent() {
        return content;
    }
    public String getContentRaw() {
        return contentRaw;
    }
    public int getResponseCount() {
        return responseCount;
    }
    public int getResponsesSeen() {
        return responsesSeen;
    }
    public String getLimitedTo() {
        return limitedTo;
    }
    public boolean isFavorite() {
        return favorite;
    }
    public int getFavoriteCount() {
        return favoriteCount;
    }
    public String getFavorers() {
        return favorers;
    }
    public boolean isReplurkable() {
        return replurkable;
    }
    public boolean isReplurked() {
        return replurked;
    }
    public String getReplurkerId() {
        return replurkerId;
    }
    public int getReplurkersCount() {
        return replurkersCount;
    }
    public String getReplurkers() {
        return replurkers;
    }

    public void setPlurkId(long plurkId) {
        this.plurkId = plurkId;
    }
    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }
    public void setQualifierTranslated(String qualifierTranslated) {
        this.qualifierTranslated = qualifierTranslated;
    }
    public void setIsUnread(int isUnread) {
        this.isUnread = isUnread;
    }
    public void setPlurkType(int plurkType) {
        this.plurkType = plurkType;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }
    public void setPosted(String posted) {
        this.posted = posted;
    }
    public void setNoComments(int noComments) {
        this.noComments = noComments;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setContentRaw(String contentRaw) {
        this.contentRaw = contentRaw;
    }
    public void setResponseCount(int responseCount) {
        this.responseCount = responseCount;
    }
    public void setResponsesSeen(int responsesSeen) {
        this.responsesSeen = responsesSeen;
    }
    public void setLimitedTo(String limitedTo) {
        this.limitedTo = limitedTo;
    }
    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }
    public void setFavorers(String favorers) {
        this.favorers = favorers;
    }
    public void setReplurkable(boolean replurkable) {
        this.replurkable = replurkable;
    }
    public void setReplurked(boolean replurked) {
        this.replurked = replurked;
    }
    public void setReplurkerId(String replurkerId) {
        this.replurkerId = replurkerId;
    }
    public void setReplurkersCount(int replurkersCount) {
        this.replurkersCount = replurkersCount;
    }
    public void setReplurkers(String replurkers) {
        this.replurkers = replurkers;
    }

    //UNLISTED
    private static final String KEY_PORN = "porn";
    private static final String KEY_MENTIONED = "mentioned";
    private static final String KEY_BOOKMARK = "bookmark";
    private static final String KEY_COINS = "coins";
    private static final String KEY_EXCLUDED = "excluded";
    private static final String KEY_HAS_GIFT = "has_gift";
    private static final String KEY_ANONYMOUS = "anonymous";
    private static final String KEY_LAST_EDITED = "last_edited";
    private static final String KEY_LANG = "lang";
    private static final String KEY_RESPONDED = "responded";

    private boolean porn = false;
    private int mentioned = 0;
    private boolean bookmark = false;
    private int coins = 0;
    private String excluded = null;
    private boolean hasGift = false;
    private boolean anonymous = false;
    private String lastEdited;
    private String lang;
    private int responded = 0;

    public boolean isPorn() {
        return porn;
    }
    public int getMentioned() {
        return mentioned;
    }
    public boolean isBookmark() {
        return bookmark;
    }
    public int getCoins() {
        return coins;
    }
    public String getExcluded() {
        return excluded;
    }
    public boolean isHasGift() {
        return hasGift;
    }
    public boolean isAnonymous() {
        return anonymous;
    }
    public String getLastEdited() {
        return lastEdited;
    }
    public String getLang() {
        return lang;
    }
    public int getResponded() {
        return responded;
    }

    public void setPorn(boolean porn) {
        this.porn = porn;
    }
    public void setMentioned(int mentioned) {
        this.mentioned = mentioned;
    }
    public void setBookmark(boolean bookmark) {
        this.bookmark = bookmark;
    }
    public void setCoins(int coins) {
        this.coins = coins;
    }
    public void setExcluded(String excluded) {
        this.excluded = excluded;
    }
    public void setHasGift(boolean hasGift) {
        this.hasGift = hasGift;
    }
    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }
    public void setLastEdited(String lastEdited) {
        this.lastEdited = lastEdited;
    }
    public void setLang(String lang) {
        this.lang = lang;
    }
    public void setResponded(int responded) {
        this.responded = responded;
    }

    private PlurkBean(){
        setBeanType();
    }

    public static PlurkBean parsePlurkBean(JSONObject jsonObject) throws JSONException {
        PlurkBean res = new PlurkBean();
        if(jsonObject.has(KEY_RESPONSES_SEEN)){
            res.setResponsesSeen(jsonObject.getInt(KEY_RESPONSES_SEEN));
        }
        if(jsonObject.has(KEY_QUALIFIER)){
            res.setQualifier(jsonObject.getString(KEY_QUALIFIER));
        }
        if(jsonObject.has(KEY_PLURK_ID)){
            res.setPlurkId(jsonObject.getLong(KEY_PLURK_ID));
        }
        if(jsonObject.has(KEY_RESPONSE_COUNT)){
            res.setResponseCount(jsonObject.getInt(KEY_RESPONSE_COUNT));
        }
        if(jsonObject.has(KEY_LIMITED_TO)){
            res.setLimitedTo(jsonObject.getString(KEY_LIMITED_TO));
        }
        if(jsonObject.has(KEY_NO_COMMENTS)){
            res.setNoComments(jsonObject.getInt(KEY_NO_COMMENTS));
        }
        if(jsonObject.has(KEY_IS_UNREAD)){
            res.setIsUnread(jsonObject.getInt(KEY_IS_UNREAD));
        }
        if(jsonObject.has(KEY_LANG)){
            res.setLang(jsonObject.getString(KEY_LANG));
        }
        if(jsonObject.has(KEY_CONTENT_RAW)){
            res.setContentRaw(jsonObject.getString(KEY_CONTENT_RAW));
        }
        if(jsonObject.has(KEY_USER_ID)){
            res.setUserId(jsonObject.getInt(KEY_USER_ID));
        }
        if(jsonObject.has(KEY_PLURK_TYPE)){
            res.setPlurkType(jsonObject.getInt(KEY_PLURK_TYPE));
        }
        if(jsonObject.has(KEY_CONTENT)){
            res.setContent(jsonObject.getString(KEY_CONTENT));
        }
        if(jsonObject.has(KEY_QUALIFIER_TRANSLATED)){
            res.setQualifierTranslated(jsonObject.getString(KEY_QUALIFIER_TRANSLATED));
        }
        if(jsonObject.has(KEY_POSTED)){
            res.setPosted(jsonObject.getString(KEY_POSTED));
        }
        if(jsonObject.has(KEY_OWNER_ID)){
            res.setOwnerId(jsonObject.getInt(KEY_OWNER_ID));
        }
        if(jsonObject.has(KEY_FAVORITE)){
            res.setFavorite(jsonObject.getBoolean(KEY_FAVORITE));
        }
        if(jsonObject.has(KEY_FAVORITE_COUNT)){
            res.setFavoriteCount(jsonObject.getInt(KEY_FAVORITE_COUNT));
        }
        if(jsonObject.has(KEY_FAVORERS)){
            res.setFavorers(jsonObject.getString(KEY_FAVORERS));
        }
        if(jsonObject.has(KEY_REPLURKABLE)){
            res.setReplurkable(jsonObject.getBoolean(KEY_REPLURKABLE));
        }
        if(jsonObject.has(KEY_REPLURKED)){
            try{
                res.setReplurked(jsonObject.getBoolean(KEY_REPLURKED));
            } catch (Exception e) {
                res.setReplurked(false);//default false (as null)
            }
        }
        if(jsonObject.has(KEY_REPLURKER_ID)){
            res.setReplurkerId(jsonObject.getString(KEY_REPLURKER_ID));
        }
        if(jsonObject.has(KEY_REPLURKERS)){
            res.setReplurkers(jsonObject.getString(KEY_REPLURKERS));
        }
        if(jsonObject.has(KEY_REPLURKERS_COUNT)){
            res.setReplurkersCount(jsonObject.getInt(KEY_REPLURKERS_COUNT));
        }

        return res;
    }

    public static PlurkBean parseFullPlurkBean(JSONObject jsonObject) throws JSONException {
        //TODO: parse more value
        return PlurkBean.parsePlurkBean(jsonObject);
    }
}
