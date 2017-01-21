package app.tabletplaza.tfa.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

import app.tabletplaza.tfa.objects.BaseObject;
import app.tabletplaza.tfa.objects.PostObject_Xenforo;
import app.tabletplaza.tfa.utilities.Tools;

/**
 * Created by SolbadguyKY on 20-Jan-17.
 */

public class JSONParser {

    public static enum JSON_OBJECT {
        POST, THREAD, USER_PROFILE
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
        }

        return null;
    }

    /**
     * Convert xenforo sang Object ba82ng tag
     *
     * @param jsonObject
     * @return
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
            List<String> urlList = Tools.extractUrls(zThumbRawString);

            if (urlList.size() > 0) {
                postObject_xenforo.setImage(urlList.get(0));
            }
        }

        return postObject_xenforo;
    }
}
