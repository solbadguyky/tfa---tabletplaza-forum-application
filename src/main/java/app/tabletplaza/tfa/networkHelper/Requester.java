package app.tabletplaza.tfa.networkHelper;

import android.content.Context;
import android.support.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.Map;

import app.tabletplaza.tfa.instances.VolleyGlobalInstance;

/**
 * Created by SolbadguyKY on 09-Feb-17.
 */

public class Requester {
    public static final String TAB = "RequestServiceHelper";
    private Context context;

    public Requester(Context context) {
        this.context = context;
    }

    public void sendPost(String postContent, String url, @Nullable final Map<String, String> params, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, listener,
                errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                if (params != null) {
                    return params;
                }
                return super.getParams();
            }

            @Override
            protected String getParamsEncoding() {
                return "UTF-8";
            }
        };
        VolleyGlobalInstance.getInstance(context).addToRequestQueue(request);
    }

}
