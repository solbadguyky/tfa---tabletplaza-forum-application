package app.tabletplaza.tfa.testing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.orhanobut.logger.Logger;
import com.zzhoujay.richtext.RichText;

import org.json.JSONObject;

import app.tabletplaza.tfa.R;
import app.tabletplaza.tfa.networkHelper.Downloader;
import app.tabletplaza.tfa.objects.BaseObject;
import app.tabletplaza.tfa.objects.PostDetailObject_Xenforo;
import app.tabletplaza.tfa.objects.PostObject;
import app.tabletplaza.tfa.objects.ThreadObject;
import app.tabletplaza.tfa.parser.JSONParser;
import app.tabletplaza.tfa.utilities.GlideUtilities;
import im.delight.android.webview.AdvancedWebView;

public class Testing_MasterDetail_Activity extends AppCompatActivity {

    AdvancedWebView mAdvancedWebView;
    BaseObject.Type objectType = BaseObject.Type.Thread;
    PostObject mPostObject;
    ThreadObject mThreadObject;
    Toolbar toolbar;
    ImageView thumbnailView;
    TextView contentView;

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
        if (getObject() != null) {
            BaseObject object = getObject();
            if (object instanceof ThreadObject) {
                mThreadObject = (ThreadObject) object;
            }
        }

    }

    void initViews() {
        //mAdvancedWebView = (AdvancedWebView) findViewById(R.id.contentScrolling_webView);
        thumbnailView = (ImageView) findViewById(R.id.content_ImageView);
        contentView = (TextView) findViewById(R.id.contentScrolling_contentView);
    }

    void setupViews() {
        if (mPostObject != null) {

        } else if (mThreadObject != null) {
            checkObjectValue(mThreadObject);
        }
    }

    ///Kiểm tra xem thử object đã có dữ liệu chưa, nếu đã có thì sẽ dùng để hiển thị ngay
    void checkObjectValue(ThreadObject threadObject) {
        ///Load nội dung bai viết
        String threadContent = threadObject.getPostDescription();
        String threadUrl = threadObject.getUrl();
        loadDataToWebView(threadContent, threadUrl);
        loadDataToLayout(threadObject);
    }

    void loadDataToWebView(String htmlData, String baseUrl) {
        // mAdvancedWebView.loadHtml(htmlData, baseUrl);
        RichText.fromHtml(htmlData).into(contentView);

    }

    void loadDataToLayout(ThreadObject object) {
        //Logger.d(postObject_xenforo.getImage());

        if (object.getImage() != null && !object.getImage().isEmpty()) {
            //Logger.d(postObject_xenforo.getImage());
            GlideUtilities.loadImageToImageView(this, object.getImage(), thumbnailView);
        }
        getSupportActionBar().setTitle(object.getName());
        getSupportActionBar().setSubtitle(object.getUserObject().getName());
    }

    /**
     * Lấy object từ root-activity
     *
     * @return postObject từ root-activity
     */
    BaseObject getObject() throws NullPointerException {
        BaseObject postObjectXenforo = (BaseObject) getIntent().getSerializableExtra(BaseObject
                .TAG);
        return postObjectXenforo;
    }

    void downloadData(String url) {
        Downloader downloader = new Downloader(this);
        downloader.downloadJSON(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // Logger.d(response);
                BaseObject baseObject = JSONParser.parseJson(response.toString(), JSONParser.JSON_OBJECT.THREAD_DETAIL);
                if (baseObject instanceof PostDetailObject_Xenforo) {
                    PostDetailObject_Xenforo postObject_xenforo = (PostDetailObject_Xenforo) baseObject;

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Logger.e(error.getMessage());
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
