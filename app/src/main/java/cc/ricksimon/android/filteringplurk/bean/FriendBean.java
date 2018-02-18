package cc.ricksimon.android.filteringplurk.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Simon on 2018/2/18.
 */

public class FriendBean extends BaseBean {

    @Override
    public void setBeanType() {
        setBeanType(FriendBean.class.getSimpleName());
    }

    private static final String KEY_VERIFIED_ACCOUNT = "verified_account";
    private static final String KEY_DEFAULT_LANG = "default_lang";
    private static final String KEY_PREMIUM = "premium";
    private static final String KEY_DATEFORMAT = "dateformat";
    private static final String KEY_HAS_PROFILE_IMAGE = "has_profile_image";
    private static final String KEY_FULL_NAME = "full_name";
    private static final String KEY_NAME_COLOR = "name_color";
    private static final String KEY_TIME_ZONE = "time_zone";
    private static final String KEY_TIMELINE_PRIVACY = "timeline_privacy";
    private static final String KEY_ID = "id";
    private static final String KEY_DISPLAY_NAME = "display_name";
    private static final String KEY_SHOW_LOCATION = "show_location";
    private static final String KEY_NICK_NAME = "nick_name";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_AVATAR = "avatar";
    private static final String KEY_SHOW_ADS = "show_ads";
    private static final String KEY_BACKGOUND_ID = "background_id";
    private static final String KEY_DATE_OF_BIRTH = "date_of_birth";
    private static final String KEY_LOCATION = "location";
    private static final String KEY_PINNED_PLURK_ID = "pinned_plurk_id";
    private static final String KEY_BDAY_PRIVACY = "bday_privacy";
    private static final String KEY_KARMA = "karma";

    private boolean verifiedAccount = false;
    private String defaultLang = null;
    private boolean premium = false;
    private int dateFormat = -1;
    private int hasProfileImage = -1;
    private String fullName = null;
    private String nameColor = null;
    private String timezone = null;
    private int timelinePrivacy = -1;
    private long id = -1;
    private String displayName = null;
    private int showLocation = 1;
    private String nickName = null;
    private int gender = -1;
    private int avatar = -1;
    private boolean showAds = false;
    private int backgroundId = -1;
    private String dateOfBirth = null;
    private String location = null;
    private long pinnedPlurkId = -1;
    private int bdayPrivacy = -1;
    private double karma = -1;

    public boolean isVerifiedAccount() {
        return verifiedAccount;
    }
    public String getDefaultLang() {
        return defaultLang;
    }
    public boolean isPremium() {
        return premium;
    }
    public int getDateFormat() {
        return dateFormat;
    }
    public int getHasProfileImage() {
        return hasProfileImage;
    }
    public String getFullName() {
        return fullName;
    }
    public String getNameColor() {
        return nameColor;
    }
    public String getTimezone() {
        return timezone;
    }
    public int getTimelinePrivacy() {
        return timelinePrivacy;
    }
    public long getId() {
        return id;
    }
    public String getDisplayName() {
        return displayName;
    }
    public int getShowLocation() {
        return showLocation;
    }
    public String getNickName() {
        return nickName;
    }
    public int getGender() {
        return gender;
    }
    public int getAvatar() {
        return avatar;
    }
    public boolean isShowAds() {
        return showAds;
    }
    public int getBackgroundId() {
        return backgroundId;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public String getLocation() {
        return location;
    }
    public long getPinnedPlurkId() {
        return pinnedPlurkId;
    }
    public int getBdayPrivacy() {
        return bdayPrivacy;
    }
    public double getKarma() {
        return karma;
    }

    public void setVerifiedAccount(boolean verifiedAccount) {
        this.verifiedAccount = verifiedAccount;
    }
    public void setDefaultLang(String defaultLang) {
        this.defaultLang = defaultLang;
    }
    public void setPremium(boolean premium) {
        this.premium = premium;
    }
    public void setDateFormat(int dateFormat) {
        this.dateFormat = dateFormat;
    }
    public void setHasProfileImage(int hasProfileImage) {
        this.hasProfileImage = hasProfileImage;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setNameColor(String nameColor) {
        this.nameColor = nameColor;
    }
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
    public void setTimelinePrivacy(int timelinePrivacy) {
        this.timelinePrivacy = timelinePrivacy;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    public void setShowLocation(int showLocation) {
        this.showLocation = showLocation;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public void setGender(int gender) {
        this.gender = gender;
    }
    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }
    public void setShowAds(boolean showAds) {
        this.showAds = showAds;
    }
    public void setBackgroundId(int backgroundId) {
        this.backgroundId = backgroundId;
    }
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setPinnedPlurkId(long pinnedPlurkId) {
        this.pinnedPlurkId = pinnedPlurkId;
    }
    public void setBdayPrivacy(int bdayPrivacy) {
        this.bdayPrivacy = bdayPrivacy;
    }
    public void setKarma(double karma) {
        this.karma = karma;
    }

    private FriendBean(){
        setBeanType();
    }

    public static FriendBean parseFriendBean(JSONObject jsonObject) throws JSONException{
        FriendBean res = new FriendBean();

        if(jsonObject.has(KEY_VERIFIED_ACCOUNT)){
            res.setVerifiedAccount(jsonObject.getBoolean(KEY_VERIFIED_ACCOUNT));
        }
        if(jsonObject.has(KEY_DEFAULT_LANG)){
            res.setDefaultLang(jsonObject.getString(KEY_DEFAULT_LANG));
        }
        if(jsonObject.has(KEY_PREMIUM)){
            res.setPremium(jsonObject.getBoolean(KEY_PREMIUM));
        }
        if(jsonObject.has(KEY_DATEFORMAT)){
            res.setDateFormat(jsonObject.getInt(KEY_DATEFORMAT));
        }
        if(jsonObject.has(KEY_HAS_PROFILE_IMAGE)){
            res.setHasProfileImage(jsonObject.getInt(KEY_HAS_PROFILE_IMAGE));
        }
        if(jsonObject.has(KEY_FULL_NAME)){
            res.setFullName(jsonObject.getString(KEY_FULL_NAME));
        }
        if(jsonObject.has(KEY_NAME_COLOR)){
            res.setNameColor(jsonObject.getString(KEY_NAME_COLOR));
        }
        if(jsonObject.has(KEY_TIME_ZONE)){
            res.setTimezone(jsonObject.getString(KEY_TIME_ZONE));
        }
        if(jsonObject.has(KEY_TIMELINE_PRIVACY)){
            res.setTimelinePrivacy(jsonObject.getInt(KEY_TIMELINE_PRIVACY));
        }
        if(jsonObject.has(KEY_ID)){
            res.setId(jsonObject.getLong(KEY_ID));
        }
        if(jsonObject.has(KEY_DISPLAY_NAME)){
            res.setDisplayName(jsonObject.getString(KEY_DISPLAY_NAME));
        }
        if(jsonObject.has(KEY_SHOW_LOCATION)){
            res.setShowLocation(jsonObject.getInt(KEY_SHOW_LOCATION));
        }
        if(jsonObject.has(KEY_NICK_NAME)){
            res.setNickName(jsonObject.getString(KEY_NICK_NAME));
        }
        if(jsonObject.has(KEY_GENDER)){
            res.setGender(jsonObject.getInt(KEY_GENDER));
        }
        if(jsonObject.has(KEY_AVATAR)){
            res.setAvatar(jsonObject.getInt(KEY_AVATAR));
        }
        if(jsonObject.has(KEY_SHOW_ADS)){
            res.setShowAds(jsonObject.getBoolean(KEY_SHOW_ADS));
        }
        if(jsonObject.has(KEY_BACKGOUND_ID)){
            res.setBackgroundId(jsonObject.getInt(KEY_BACKGOUND_ID));
        }
        if(jsonObject.has(KEY_DATE_OF_BIRTH)){
            res.setDateOfBirth(jsonObject.getString(KEY_DATE_OF_BIRTH));
        }
        if(jsonObject.has(KEY_LOCATION)){
            res.setLocation(jsonObject.getString(KEY_LOCATION));
        }
        if(jsonObject.has(KEY_PINNED_PLURK_ID)){
            try {
                res.setPinnedPlurkId(jsonObject.getLong(KEY_PINNED_PLURK_ID));
            } catch (Exception e) {
                res.setPinnedPlurkId(-1);//default -1 (as null)
            }

        }
        if(jsonObject.has(KEY_BDAY_PRIVACY)){
            res.setBdayPrivacy(jsonObject.getInt(KEY_BDAY_PRIVACY));
        }
        if(jsonObject.has(KEY_KARMA)){
            res.setKarma(jsonObject.getDouble(KEY_KARMA));
        }

        return res;
    }
}