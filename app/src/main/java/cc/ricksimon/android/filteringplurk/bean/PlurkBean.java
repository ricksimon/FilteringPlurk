package cc.ricksimon.android.filteringplurk.bean;

/**
 * Created by Simon on 2017/11/3.
 */

public class PlurkBean {
    /*
    plurk_id: The unique Plurk id, used for identification of the plurk.
    qualifier: The English qualifier, can be "says", show all: Show example data
    qualifier_translated: Only set if the language is not English, will be the translated qualifier. Can be "siger" if plurk.lang is "da" (Danish).
    is_unread: Specifies if the plurk is read, unread or muted. Show example data
    plurk_type: Specifies what type of plurk it is and if the plurk has been responded by the user. The value of plurk_type is only correct when calling getPlurks with "responded" filter (this is done for perfomance and caching reasons).
    user_id: Which timeline does this Plurk belong to.
    owner_id: Who is the owner/poster of this plurk. For anonymous plurk, this will be overrided by user "anonymous" (uid: 99999).
    posted: The date this plurk was posted.
    no_comments: If set to 1, then responses are disabled for this plurk.
    If set to 2, then only friends can respond to this plurk.
    content: The formatted content, emoticons and images will be turned into IMG tags etc.
    content_raw: The raw content as user entered it, useful when editing plurks or if you want to format the content differently.
    response_count: How many responses does the plurk have.
    responses_seen: How many of the responses have the user read. This is automatically updated when fetching responses or marking a plurk as read.
    limited_to: If the Plurk is public limited_to is null. If the Plurk is posted to a user's friends then limited_to is [0]. If limited_to is [1,2,6,3] then it's posted only to these user ids.
    favorite: True if current user has liked given plurk.
    favorite_count: Number of users who liked given plurk.
    favorers: List of ids of users who liked given plurk (can be truncated).
    replurkable: True if plurk can be replurked.
    replurked: True if plurk has been replurked by current user.
    replurker_id: ID of a user who has replurked given plurk to current user's timeline.
    replurkers_count: Number of users who replurked given plurk.
    replurkers: List of ids of users who replurked given plurk (can be truncated).
         */
}
