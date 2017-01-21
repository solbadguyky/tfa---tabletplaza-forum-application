package app.tabletplaza.tfa.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import app.tabletplaza.tfa.objects.BaseObject;
import app.tabletplaza.tfa.objects.PostObject_Xenforo;

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
                // Logger.d(jsonObject);

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

        return postObject_xenforo;
    }
}
