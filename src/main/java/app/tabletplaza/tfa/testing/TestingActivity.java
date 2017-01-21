package app.tabletplaza.tfa.testing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;
import com.orhanobut.logger.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import app.tabletplaza.tfa.R;
import app.tabletplaza.tfa.adapter.adapterObjects.Post_ObjectAdapter_Xenforo;
import app.tabletplaza.tfa.factory.AdapterObjectFactories;
import app.tabletplaza.tfa.networkHelper.Downloader;
import app.tabletplaza.tfa.objects.BaseObject;
import app.tabletplaza.tfa.objects.PostObject_Xenforo;
import app.tabletplaza.tfa.parser.JSONParser;


public class TestingActivity extends AppCompatActivity {

    private RecyclerView itemsRecyclerView;
    private FastItemAdapter<Post_ObjectAdapter_Xenforo> fastAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        Logger.init();

        initView();
        setupView();
        downloadTestJSON();
    }

    void initView() {
        itemsRecyclerView = (RecyclerView) findViewById(R.id.testingActivity_recyclerView_ItemsRecyclerView);
    }

    void setupView() {
        itemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        fastAdapter = new FastItemAdapter<>();

        itemsRecyclerView.setAdapter(fastAdapter);
    }

    public void downloadTestJSON() {
        Downloader downloader = new Downloader(this);

        String testingUrl = "http://thegioitinhte.com/api.php?action=getThreads&hash=4b845c3efd9a6c6b1ca97d82fc863397&limit=20&order=d&order_by=post_date";
        Logger.d(testingUrl);
        downloader.downloadJSON(testingUrl, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (response.has("threads")) {
                    try {
                        JSONArray postArr = response.getJSONArray("threads");
                        ArrayList<PostObject_Xenforo> xenforosArrItems = new ArrayList<>();
                        for (int i = 0; i < postArr.length(); i++) {
                            String rawString = postArr.get(i).toString();
                            Logger.d(rawString);
                            BaseObject baseObject = JSONParser.parseJson(rawString, JSONParser.JSON_OBJECT.POST);
                            if (baseObject instanceof PostObject_Xenforo) {
                                PostObject_Xenforo postObject_xenforo = (PostObject_Xenforo) baseObject;
                                xenforosArrItems.add(postObject_xenforo);

                                //Logger.d(postObject_xenforo.getName());
                            }

                        }
                        loadData(xenforosArrItems);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    // JSONParser.parseJson()
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

    void loadData(ArrayList<PostObject_Xenforo> items) {
        Logger.d("loadData|items.size = %d", items.size());
        ArrayList<Post_ObjectAdapter_Xenforo> objectsAdapter = AdapterObjectFactories.convertObjectsToAdapter(items);
        fastAdapter.set(objectsAdapter);

        fastAdapter.notifyAdapterDataSetChanged();
    }
}
