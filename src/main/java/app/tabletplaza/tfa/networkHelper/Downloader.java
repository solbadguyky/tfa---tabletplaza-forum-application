package app.tabletplaza.tfa.networkHelper;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import app.tabletplaza.tfa.instances.VolleyGlobalInstance;

/**
 * Created by SolbadguyKY on 16-Jan-17.
 */

public class Downloader {

    public static final String TAB = "ServiceHelper";
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

}
