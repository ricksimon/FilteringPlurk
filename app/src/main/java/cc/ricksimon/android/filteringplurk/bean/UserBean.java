package cc.ricksimon.android.filteringplurk.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Simon on 2017/11/3.
 */

public class UserBean {
    public enum BDayPrivacy{
        HIDE_BIRTHDAY(0),
        SHOW_ONLY_DAY_MONTH(1),
        SHOW_ALL(2);

        private int bDayPrivacy;
        BDayPrivacy(int bDayPrivacy){
            this.bDayPrivacy = bDayPrivacy;
        }
    }

    public enum Gender{
        FEMALE(0),
        MALE(1),
        OTHER(2);

        private int gender;
        Gender(int gender){
            this.gender = gender;
        }
    }

    public enum Relationship{
        NOT_SAYING("not_saying"),
        SINGLE("single"),
        MARRIED("married"),
        DIVORCED("divorced"),
        ENGAGED("engaged"),
        IN_RELATIONSHIP("in_relationship"),
        COMPLICATED("complicated"),
        WIDOWED("widowed"),
        UNSTABLE_RELATIONSHIP("unstable_relationship"),
        OPEN_RELATIONSHIP("open_relationship");

        private String relationship;
        Relationship(String relationship){
            this.relationship = relationship;
        }
    }

    private static final String KEY_ID = "id";
    private static final String KEY_NICK_NAME = "nick_name";
    private static final String KEY_DISPLAY_NAME = "display_name";
    private static final String KEY_PREMIUM = "premium";
    private static final String KEY_HAS_PROFILE_IMAGE = "has_profile_image";
    private static final String KEY_AVATAR = "avatar";
    private static final String KEY_LOCATION = "location";
    private static final String KEY_DEFAULT_LANG = "default_lang";
    private static final String KEY_DATE_OF_BIRTH = "date_of_birthday";
    private static final String KEY_BDAY_PRIVACY = "bday_privacy";
    private static final String KEY_FULL_NAME = "full_name";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_KARMA = "karma";
    private static final String KEY_RECRUITED = "recruited";
    private static final String KEY_RELATIONSHIP = "relationship";

    private int id = 0;
    private String nickName = null;
    private String displayName = null;
    private boolean premium = false;
    private int hasProfileImage = 0;
    private int avatar = 0;
    private String location = null;
    private String defaultLang = null;
    private String dateOfBirth = null;
    private int bdayPrivacy = 0;
    private String fullName = null;
    private int gender = 0;
    private double karma = 0;
    private int recruited = 0;
    private String relationship = null;

    public int getId() {
        return id;
    }
    public String getNickName() {
        return nickName;
    }
    public String getDisplayName() {
        return displayName;
    }
    public boolean isPremium() {
        return premium;
    }
    public int getHasProfileImage() {
        return hasProfileImage;
    }
    public int getAvatar() {
        return avatar;
    }
    public String getLocation() {
        return location;
    }
    public String getDefaultLang() {
        return defaultLang;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public int getBdayPrivacy() {
        return bdayPrivacy;
    }
    public String getFullName() {
        return fullName;
    }
    public int getGender() {
        return gender;
    }
    public double getKarma() {
        return karma;
    }
    public int getRecruited() {
        return recruited;
    }
    public String getRelationship() {
        return relationship;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    public void setPremium(boolean premium) {
        this.premium = premium;
    }
    public void setHasProfileImage(int hasProfileImage) {
        this.hasProfileImage = hasProfileImage;
    }
    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setDefaultLang(String defaultLang) {
        this.defaultLang = defaultLang;
    }
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public void setBdayPrivacy(int bdayPrivacy) {
        this.bdayPrivacy = bdayPrivacy;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setGender(int gender) {
        this.gender = gender;
    }
    public void setKarma(double karma) {
        this.karma = karma;
    }
    public void setRecruited(int recruited) {
        this.recruited = recruited;
    }
    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    //UNLISTED
    private static final String KEY_AVATAR_BIG = "avatar_big";
    private static final String KEY_AVATAR_MEDIUM = "avatar_medium";
    private static final String KEY_AVATAR_SMALL = "avatar_small";
    private static final String KEY_FANS_COUNT = "fans_count";
    private static final String KEY_PROFILE_VIEWS = "profile_views";
    private static final String KEY_RESPONSE_COUNT = "response_count";
    private static final String KEY_PLURKS_COUNT = "plurks_count";
    private static final String KEY_FRIENDS_COUNT = "friends_count";
    private static final String KEY_BADGES = "badges";
    private static final String KEY_VERIFIED_ACCOUNT = "verified_account";
    private static final String KEY_PRIVACY = "privacy";
    private static final String KEY_SHOW_ADS = "show_ads";
    private static final String KEY_BACKGROUND_ID = "background_id";
    private static final String KEY_NAME_COLOR = "name_color";
    private static final String KEY_DATEFORMAT = "dateformat";
    private static final String KEY_TIMELINE_PRIVACY = "timeline_privacy";
    private static final String KEY_FILTER_PORN = "filter_porn";
    private static final String KEY_SHOW_LOCATION = "show_location";
    private static final String KEY_TIMEZONE = "timezone";
    private static final String KEY_SETUP_FACEBOOK_SYNC = "setup_facebook_sync";
    private static final String KEY_SETUP_TWITTER_SYNC = "setup_twitter_sync";
    private static final String KEY_SETUP_WEIBO_SYNC = "setup_weibo_sync";
    private static final String KEY_POST_ANONYMOUS_PLURK = "post_anonymous_plurk";
    private static final String KEY_ACCEPT_PRIVATE_PLURK_FROM = "accept_private_plurk_from";
    private static final String KEY_CREATURE_URL = "creature_url";
    private static final String KEY_PINNED_PLURK_ID = "pinned_plurk_id";
    private static final String KEY_LINK_FACEBOOK = "link_facebook";
    private static final String KEY_FILTER_ANONYMOUS = "filter_anonymous";
    private static final String KEY_FILTER_KEYWORDS = "filter_keywords";
    private static final String KEY_PAGE_TITLE = "page_title";
    private static final String KEY_ABOUT = "about";

    private String avatarBig = null;
    private String avatarMedium = null;
    private String avatarSmall = null;
    private int fansCount = 0;
    private int profileViews = 0;
    private int responseCount = 0;
    private int plurksCount = 0;
    private int friendsCount = 0;
    private String badges = null;
    private boolean verifiedAccount = false;
    private String privacy = null;
    private boolean showAds = true;
    private int backgroundId = 0;
    private String nameColor = null;
    private int dateformat = 0;
    private int timelinePrivacy = 0;
    private String filterPorn = null;
    private int showLocation = 0;
    private String timezone = null;
    private boolean setupFacebookSync = false;
    private boolean setupTwitterSync = false;
    private boolean setupWeiboSync = false;
    private boolean postAnonymousPlurk = false;
    private String acceptPrivatePlurkFrom = null;
    private String creatureUrl = null;
    private String pinnedPlurkId = null;
    private boolean linkFacebook = false;
    private int filterAnonymous = 0;
    private String filterKeywords = null;
    private String pageTitle = null;
    private String about = null;

    public String getAvatarBig() {
        return avatarBig;
    }
    public String getAvatarMedium() {
        return avatarMedium;
    }
    public String getAvatarSmall() {
        return avatarSmall;
    }
    public int getFansCount() {
        return fansCount;
    }
    public int getProfileViews() {
        return profileViews;
    }
    public int getResponseCount() {
        return responseCount;
    }
    public int getPlurksCount() {
        return plurksCount;
    }
    public int getFriendsCount() {
        return friendsCount;
    }
    public String getBadges() {
        return badges;
    }
    public boolean isVerifiedAccount() {
        return verifiedAccount;
    }
    public String getPrivacy() {
        return privacy;
    }
    public boolean isShowAds() {
        return showAds;
    }
    public int getBackgroundId() {
        return backgroundId;
    }
    public String getNameColor() {
        return nameColor;
    }
    public int getDateformat() {
        return dateformat;
    }
    public int getTimelinePrivacy() {
        return timelinePrivacy;
    }
    public String getFilterPorn() {
        return filterPorn;
    }
    public int getShowLocation() {
        return showLocation;
    }
    public String getTimezone() {
        return timezone;
    }
    public boolean isSetupFacebookSync() {
        return setupFacebookSync;
    }
    public boolean isSetupTwitterSync() {
        return setupTwitterSync;
    }
    public boolean isSetupWeiboSync() {
        return setupWeiboSync;
    }
    public boolean isPostAnonymousPlurk() {
        return postAnonymousPlurk;
    }
    public String getAcceptPrivatePlurkFrom() {
        return acceptPrivatePlurkFrom;
    }
    public String getCreatureUrl() {
        return creatureUrl;
    }
    public String getPinnedPlurkId() {
        return pinnedPlurkId;
    }
    public boolean isLinkFacebook() {
        return linkFacebook;
    }
    public int getFilterAnonymous() {
        return filterAnonymous;
    }
    public String getFilterKeywords() {
        return filterKeywords;
    }
    public String getPageTitle() {
        return pageTitle;
    }
    public String getAbout() {
        return about;
    }

    public void setAvatarMedium(String avatarMedium) {
        this.avatarMedium = avatarMedium;
    }
    public void setAvatarBig(String avatarBig) {
        this.avatarBig = avatarBig;
    }
    public void setAvatarSmall(String avatarSmall) {
        this.avatarSmall = avatarSmall;
    }
    public void setFansCount(int fansCount) {
        this.fansCount = fansCount;
    }
    public void setProfileViews(int profileViews) {
        this.profileViews = profileViews;
    }
    public void setResponseCount(int responseCount) {
        this.responseCount = responseCount;
    }
    public void setPlurksCount(int plurksCount) {
        this.plurksCount = plurksCount;
    }
    public void setFriendsCount(int friendsCount) {
        this.friendsCount = friendsCount;
    }
    public void setBadges(String badges) {
        this.badges = badges;
    }
    public void setVerifiedAccount(boolean verifiedAccount) {
        this.verifiedAccount = verifiedAccount;
    }
    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }
    public void setShowAds(boolean showAds) {
        this.showAds = showAds;
    }
    public void setBackgroundId(int backgroundId) {
        this.backgroundId = backgroundId;
    }
    public void setNameColor(String nameColor) {
        this.nameColor = nameColor;
    }
    public void setDateformat(int dateformat) {
        this.dateformat = dateformat;
    }
    public void setTimelinePrivacy(int timelinePrivacy) {
        this.timelinePrivacy = timelinePrivacy;
    }
    public void setFilterPorn(String filterPorn) {
        this.filterPorn = filterPorn;
    }
    public void setShowLocation(int showLocation) {
        this.showLocation = showLocation;
    }
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
    public void setSetupFacebookSync(boolean setupFacebookSync) {
        this.setupFacebookSync = setupFacebookSync;
    }
    public void setSetupTwitterSync(boolean setupTwitterSync) {
        this.setupTwitterSync = setupTwitterSync;
    }
    public void setSetupWeiboSync(boolean setupWeiboSync) {
        this.setupWeiboSync = setupWeiboSync;
    }
    public void setPostAnonymousPlurk(boolean postAnonymousPlurk) {
        this.postAnonymousPlurk = postAnonymousPlurk;
    }
    public void setAcceptPrivatePlurkFrom(String acceptPrivatePlurkFrom) {
        this.acceptPrivatePlurkFrom = acceptPrivatePlurkFrom;
    }
    public void setCreatureUrl(String creatureUrl) {
        this.creatureUrl = creatureUrl;
    }
    public void setPinnedPlurkId(String pinnedPlurkId) {
        this.pinnedPlurkId = pinnedPlurkId;
    }
    public void setLinkFacebook(boolean linkFacebook) {
        this.linkFacebook = linkFacebook;
    }
    public void setFilterAnonymous(int filterAnonymous) {
        this.filterAnonymous = filterAnonymous;
    }
    public void setFilterKeywords(String filterKeywords) {
        this.filterKeywords = filterKeywords;
    }
    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }
    public void setAbout(String about) {
        this.about = about;
    }

    private UserBean(){
    }

    public static UserBean parseSimpleUserBean(JSONObject jsonObject) throws JSONException {
        UserBean res = new UserBean();
        if(jsonObject.has(KEY_DISPLAY_NAME)){
            res.setDisplayName(jsonObject.getString(KEY_DISPLAY_NAME));
        }
        if(jsonObject.has(KEY_GENDER)){
            res.setGender(jsonObject.getInt(KEY_GENDER));
        }
        if(jsonObject.has(KEY_NICK_NAME)){
            res.setNickName(jsonObject.getString(KEY_NICK_NAME));
        }
        if(jsonObject.has(KEY_HAS_PROFILE_IMAGE)){
            res.setHasProfileImage(jsonObject.getInt(KEY_HAS_PROFILE_IMAGE));
        }
        if(jsonObject.has(KEY_ID)){
            res.setId(jsonObject.getInt(KEY_ID));
        }
        if(jsonObject.has(KEY_AVATAR)){
            try {
                res.setAvatar(jsonObject.getInt(KEY_AVATAR));
            }catch(Exception e){
                try{
                    res.setAvatar(Integer.parseInt(jsonObject.getString(KEY_AVATAR)));
                }catch (Exception e2){
                    res.setAvatar(0);
                }
            }
        }
        return res;
    }
    public static UserBean parseUserBean(JSONObject jsonObject) throws JSONException {
        /*
         * "is_channel": 0, <- what is this?
         */
        UserBean res = UserBean.parseSimpleUserBean(jsonObject);
        if(jsonObject.has(KEY_LOCATION)){
            res.setLocation(jsonObject.getString(KEY_LOCATION));
        }
        if(jsonObject.has(KEY_DATE_OF_BIRTH)){
            res.setDateOfBirth(jsonObject.getString(KEY_DATE_OF_BIRTH));
        }
        if(jsonObject.has(KEY_RELATIONSHIP)){
            res.setRelationship(jsonObject.getString(KEY_RELATIONSHIP));
        }
        if(jsonObject.has(KEY_FULL_NAME)){
            res.setFullName(jsonObject.getString(KEY_FULL_NAME));
        }
        if(jsonObject.has(KEY_RECRUITED)){
            res.setRecruited(jsonObject.getInt(KEY_RECRUITED));
        }
        if(jsonObject.has(KEY_KARMA)){
            res.setKarma(jsonObject.getDouble(KEY_KARMA));
        }
        return res;
    }
    public static UserBean parseFullUserBean(JSONObject jsonObject) throws JSONException {
        //TODO: parse more value
        return UserBean.parseUserBean(jsonObject);
    }
}
