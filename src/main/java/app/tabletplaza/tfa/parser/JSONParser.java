package app.tabletplaza.tfa.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.orhanobut.logger.Logger;

import java.util.List;

import app.tabletplaza.tfa.objects.BaseObject;
import app.tabletplaza.tfa.objects.PostDetailObject_Xenforo;
import app.tabletplaza.tfa.objects.PostObject_Xenforo;
import app.tabletplaza.tfa.objects.ThreadObject;
import app.tabletplaza.tfa.objects.UserObject;
import app.tabletplaza.tfa.utilities.Tools;

/**
 * Created by SolbadguyKY on 20-Jan-17.
 */

public class JSONParser {

    public static enum JSON_OBJECT {
        POST, THREAD, USER_PROFILE, THREAD_DETAIL
    }

    public static enum FLATFORM {
        WORDPRESS, XENFORO
    }

    public static BaseObject parseJson(String jsonRawString, JSON_OBJECT jsonType) throws JSONException {
        switch (jsonType) {
            case POST:
                JSONObject jsonObject = JSON.parseObject(jsonRawString);
                //Logger.d(jsonObject);
                return parseToXenforo_PostObject(jsonObject);
            case THREAD:
                jsonObject = JSON.parseObject(jsonRawString);
                //Logger.d(jsonObject);
                return parseToThreadObject(jsonObject);
            case THREAD_DETAIL:
                jsonObject = JSON.parseObject(jsonRawString);
                //Logger.d(jsonObject);
                return parseToXenforo_PostDetailObject(jsonObject);
        }

        return null;
    }

    /**
     * Convert xenforo sang Object bằng tag
     *
     * @param jsonObject jsonObject tải về bằng XenAPI
     * @return PostObject đã dược filled in data
     */
    private static PostObject_Xenforo parseToXenforo_PostObject(JSONObject jsonObject) {
        PostObject_Xenforo postObject_xenforo = new PostObject_Xenforo();
        postObject_xenforo.setId(jsonObject.getLong("post_id"));
        postObject_xenforo.setThreadId(jsonObject.getLong("thread_id"));
        postObject_xenforo.setUserId(jsonObject.getLong("user_id"));
        postObject_xenforo.setCreatedDate(jsonObject.getLong("post_date"));
        postObject_xenforo.setUsername(jsonObject.getString("username"));
        postObject_xenforo.setName(jsonObject.getString("title"));
        postObject_xenforo.setUrl(jsonObject.getString("absolute_url"));
        postObject_xenforo.setPostDescription(jsonObject.getString("message"));
        postObject_xenforo.setReplyCount(jsonObject.getIntValue("reply_count"));
        postObject_xenforo.setViewCount(jsonObject.getIntValue("view_count"));
        postObject_xenforo.setLikeCount(jsonObject.getIntValue("first_post_likes"));

        if (jsonObject.getString("z_thumb") != null) {
            String zThumbRawString = jsonObject.getString("z_thumb");
            try {
                List<String> urlList = Tools.extractUrls(zThumbRawString);
                Logger.d(urlList);
                int count = 0;
                while (count < urlList.size()) {
                    for (String supportedType : Tools.ImageSupportedTypes) {
                        if (urlList.get(count).contains(supportedType)) {
                            postObject_xenforo.setImage(urlList.get(count));
                            Logger.d(urlList.get(count));
                            break;
                        }
                    }

                    count++;
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }

        return postObject_xenforo;
    }

    /**
     * Convert sang Thread Object
     *
     * @param jsonObject jsonObject tải về bằng XenAPI
     * @return PostObject đã dược filled in data
     */
    private static ThreadObject parseToThreadObject(JSONObject jsonObject) {
        ThreadObject threadObject = new ThreadObject();
        threadObject.setId(jsonObject.getLong(ThreadObject.ThreadObjectKey.THREAD_ID.getKey()));
        threadObject.setCreatedDate(jsonObject.getLong(ThreadObject.ThreadObjectKey.THREAD_CREATEDDATE.getKey()));
        threadObject.setName(jsonObject.getString(ThreadObject.ThreadObjectKey.THREAD_TITLE.getKey()));
        threadObject.setUrl(jsonObject.getString(ThreadObject.ThreadObjectKey.THREAD_URL.getKey()));
        threadObject.setPostDescription(jsonObject.getString(ThreadObject.ThreadObjectKey.THREAD_DESCRIPTION.getKey()));
        threadObject.setImage(jsonObject.getString(ThreadObject.ThreadObjectKey.THREAD_THUMBNAIL.getKey()));
        threadObject.setThreadViewCount(jsonObject.getInteger(ThreadObject.ThreadObjectKey.THREAD_VIEWCOUNT.getKey()));
        threadObject.setThreadReplyCount(jsonObject.getInteger(ThreadObject.ThreadObjectKey.THREAD_REPLYCOUNT.getKey()));

        ///Chuẩn bị userid
        UserObject userObject = new UserObject();
        userObject.setId(jsonObject.getLong(ThreadObject.ThreadObjectKey.THREAD_USER_ID.getKey()));
        userObject.setName(jsonObject.getString(ThreadObject.ThreadObjectKey.THREAD_USER_NAME.getKey()));
        userObject.setImage(jsonObject.getString(ThreadObject.ThreadObjectKey.THREAD_USER_AVATAR.getKey()));
        threadObject.setUserObject(userObject);

        return threadObject;
    }


    /**
     * Convert xenforo sang Object bằng tag
     *
     * @param jsonObject jsonObject tải về bằng XenAPI
     * @return PostObject đã dược filled in data
     */
    private static PostDetailObject_Xenforo parseToXenforo_PostDetailObject(JSONObject jsonObject) {
        PostObject_Xenforo postObject_xenforo = parseToXenforo_PostObject(jsonObject);


        PostDetailObject_Xenforo postDetailObject_xenforo = new PostDetailObject_Xenforo(postObject_xenforo);
        postDetailObject_xenforo.setPostDescription_full(jsonObject.getString("postDescription_full"));

        if (postObject_xenforo.getImage() == null || postObject_xenforo.getImage().isEmpty()) {
            String zThumbRawString = postDetailObject_xenforo.getPostDescription_full();
            try {
                List<String> urlList = Tools.extractUrls(zThumbRawString);
                Logger.d(urlList);
                int count = 0;
                while (count < urlList.size()) {
                    for (String supportedType : Tools.ImageSupportedTypes) {
                        if (urlList.get(count).contains(supportedType)) {
                            postDetailObject_xenforo.setImage(urlList.get(count));
                            break;
                        }
                    }

                    count++;
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

        }
        return postDetailObject_xenforo;
    }
}
