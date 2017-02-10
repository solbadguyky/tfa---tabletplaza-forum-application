package app.tabletplaza.tfa.testing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.orhanobut.logger.Logger;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import app.tabletplaza.tfa.R;
import app.tabletplaza.tfa.networkHelper.Downloader;
import app.tabletplaza.tfa.objects.BaseObject;
import app.tabletplaza.tfa.objects.PostDetailObject_Xenforo;
import app.tabletplaza.tfa.objects.PostObject;
import app.tabletplaza.tfa.objects.PostObject_Xenforo;
import app.tabletplaza.tfa.parser.JSONParser;
import app.tabletplaza.tfa.utilities.GlideUtilities;
import im.delight.android.webview.AdvancedWebView;

public class Testing_MasterDetail_Activity extends AppCompatActivity {

    AdvancedWebView mAdvancedWebView;
    PostObject_Xenforo mPostObject;
    Toolbar toolbar;
    ImageView thumbnailView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing_master_detail);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initValue();
        initViews();
        setupViews();
    }

    void initValue() {
        mPostObject = getPostObject();
    }

    void initViews() {
        mAdvancedWebView = (AdvancedWebView) findViewById(R.id.contentScrolling_webView);
        thumbnailView = (ImageView) findViewById(R.id.content_ImageView);
    }

    void setupViews() {
        if (mPostObject != null) {
            ///download post data từ Xen_API
            String url = String.format("http://muabanonline.org/api.php?action=getThread&hash=***&value=%d", mPostObject.getThreadId());
            try {
                url = URLEncoder.encode(url, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            Downloader downloader = new Downloader(this);
            downloader.downloadJSON(url, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    // Logger.d(response);
                    BaseObject baseObject = JSONParser.parseJson(response.toString(), JSONParser.JSON_OBJECT.THREAD_DETAIL);
                    if (baseObject instanceof PostDetailObject_Xenforo) {
                        PostDetailObject_Xenforo postObject_xenforo = (PostDetailObject_Xenforo) baseObject;
                        loadDataToLayout(postObject_xenforo);
                        loadDataToWebView(postObject_xenforo.getPostDescription_full(), postObject_xenforo.getUrl());
                        //Logger.d(postObject_xenforo.getPostDescription_full());
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Logger.e(error.getMessage());
                }
            });
        }
    }

    void loadDataToWebView(String htmlData, String baseUrl) {
        mAdvancedWebView.loadHtml(htmlData, baseUrl);
    }

    void loadDataToLayout(PostDetailObject_Xenforo postObject_xenforo) {
        //Logger.d(postObject_xenforo.getImage());

        if (postObject_xenforo.getImage() != null) {
            //Logger.d(postObject_xenforo.getImage());
            GlideUtilities.loadImageToImageView(this, postObject_xenforo.getImage(), thumbnailView);
        }
        //toolbar.setTitle(postObject_xenforo.getName());
    }

    /**
     * Lấy postobject từ root-activity
     *
     * @return postObject từ root-activity
     */
    PostObject_Xenforo getPostObject() throws NullPointerException {
        PostObject_Xenforo postObjectXenforo = (PostObject_Xenforo) getIntent().getSerializableExtra(PostObject
                .TAG);

        return postObjectXenforo;
    }

    public static PostObject_Xenforo getDummyObject() {
        PostObject_Xenforo dummyObject = new PostObject_Xenforo();
        dummyObject.setThreadId(665L);
        return dummyObject;
    }
}
