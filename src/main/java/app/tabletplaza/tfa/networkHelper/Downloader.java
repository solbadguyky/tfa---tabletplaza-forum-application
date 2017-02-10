package app.tabletplaza.tfa.networkHelper;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import app.tabletplaza.tfa.instances.VolleyGlobalInstance;
import app.tabletplaza.tfa.utilities.ACache;

/**
 * Created by SolbadguyKY on 16-Jan-17.
 */

public class Downloader {
    public static final String TAG = "DownloadServiceHelper";
    public static final String NOKEY = "nokeyvalue";
    private Context context;

    public Downloader(Context context) {
        this.context = context;
    }


    /**
     * Tải JSON từ server
     *
     * @param url
     * @param listener
     * @param errorListener
     */
    public void downloadJSON(String url, Response.Listener<JSONObject> listener,
                             Response.ErrorListener errorListener) {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, listener,
                errorListener);
        VolleyGlobalInstance.getInstance(context).addToRequestQueue(request);
    }

    /**
     * Tải JSON từ server nhưng có kiểm tra khi tải xong
     *
     * @param url
     * @param listener
     * @param errorListener
     * @param finishListener
     */
    public void downloadJSON(String url, Response.Listener<JSONObject> listener,
                             Response.ErrorListener errorListener,
                             RequestQueue.RequestFinishedListener<JSONObject> finishListener) {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, listener,
                errorListener);
        VolleyGlobalInstance.getInstance(context).addToRequestQueueWithCompleteListener(request,
                finishListener);
    }

    /**
     * Những dữ liệu còn thiếu sẽ được xử lí bởi bộ Caching_Downloading của ứng dụng (Dùng trong trường
     * hợp Restful API chưa có dữ liệu đó
     * MissingKey được dùng để kiểm tra dữ liệu đã có hay chưa
     * Các trạng thái của DownloadMissingData bao gồm: NO_DATA , DOWNLOADING
     * <p>
     * Nếu nơi request messingKey vẫn còn hiển thị trên màn hình thì tiến hành cập nhật thông tin tại đó
     *
     * @param context
     * @param missingKey
     * @param missingJsonTag
     * @param url
     * @param finishListener
     */
    public static void downloadMissingData(Context context, String missingKey, final String missingJsonTag, String url,
                                           RequestQueue.RequestFinishedListener<JSONObject> finishListener) {
        ACache aCache = ACache.get(context);
        Object object = aCache.getAsObject(missingKey);
        if (object == null) {
            ///Key chưa tồn tại, tiến hành tải key dựa vào url cho sẵn, key sau khi được tải sẽ được
            // lưu vào bộ nhớ đệm
            JsonObjectRequest request = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    ///Cache missingkey
                    if (response.has(missingJsonTag)) {
                        try {
                            //Logger.d(response.get(missingJsonTag));
                            if (response.get(missingJsonTag) == null || response.getString(missingJsonTag).isEmpty()) {
                                if (missingJsonTag.equals("postThumbnail")) {
                                    ///postThumbnail hiện vẫn chưa được hỗ trợ bởi XenAPI, sẽ extract
                                    // từ postDescription_full

                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    ///Đánh dấu key không tồn tại
                }
            });
            VolleyGlobalInstance.getInstance(context).addToRequestQueueWithCompleteListener(request, finishListener);
        } else {
            Logger.d(object);
        }

    }

}
