package app.tabletplaza.tfa.testing;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.ExpandableDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.orhanobut.logger.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import app.tabletplaza.tfa.R;
import app.tabletplaza.tfa.adapter.adapterObjects.ThreadAdapter;
import app.tabletplaza.tfa.instances.DefaultInstances;
import app.tabletplaza.tfa.networkHelper.Downloader;
import app.tabletplaza.tfa.objects.BaseObject;
import app.tabletplaza.tfa.objects.PagingObject;
import app.tabletplaza.tfa.objects.ThreadObject;
import app.tabletplaza.tfa.parser.JSONParser;


public class Testing_DownloadingModul_Activity extends AppCompatActivity {

    private AppBarLayout appBarLayout;
    private RecyclerView itemsRecyclerView;
    // private FastItemAdapter<ThreadAdapter_FastAdapter> fastAdapter;
    private ThreadAdapter adapter;
    private PagingObject pagingObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        //Logger.init();

        initValue();
        initView();
        setupView();
        buildDrawer();

        ///Bắt đầu tải dữ liệu
        downloadData();
    }

    void initValue() {
        pagingObject = new PagingObject("http://muabanonline.org/api.php?action=getThreads&hash="
                + DefaultInstances.Default_API_Key, 0);
    }

    void initView() {
        itemsRecyclerView = (RecyclerView) findViewById(R.id.testingActivity_recyclerView_ItemsRecyclerView);
        appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
    }

    void setupView() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        itemsRecyclerView.setLayoutManager(layoutManager);

        adapter = new ThreadAdapter(R.layout.post_view);

        itemsRecyclerView.setAdapter(adapter);

        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (pagingObject.getOnLoading() != PagingObject.STATUS.OnLoading) {
                    downloadData();
                }
            }
        });
        adapter.setEnableLoadMore(true);

        pagingObject.setOnLoadingListener(new PagingObject.OnLoadingListener() {
            @Override
            public void onReset() {
                Logger.d("onReset");
                if (adapter.isLoading()) {
                    adapter.loadMoreFail();
                }

                adapter.setNewData(null);

                pagingObject.currentPage = 0;
                downloadData();
            }

            @Override
            public void complete() {
                Logger.d("complete");

                pagingObject.setOnLoading(PagingObject.STATUS.Free);
            }

            @Override
            public void freeing() {
                Logger.d("freeing");

                adapter.loadMoreComplete();
            }
        });

        itemsRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(Testing_DownloadingModul_Activity.this, "Clicked: " + position, Toast.LENGTH_SHORT).show();
                if (adapter.getItem(position) instanceof ThreadObject) {
                    ThreadObject threadObject = ((ThreadObject) (adapter.getItem(position)));
                    Toast.makeText(Testing_DownloadingModul_Activity.this, threadObject.getName(), Toast.LENGTH_SHORT).show();
                    Intent detailIntent = new Intent(Testing_DownloadingModul_Activity.this, Testing_MasterDetail_Activity.class);
                    detailIntent.putExtra(BaseObject.TAG, threadObject);
                    Testing_DownloadingModul_Activity.this.startActivity(detailIntent);

                }
            }
        });
    }

    void buildDrawer() {
        ///Muabanonline.org
        ExpandableDrawerItem muaBanOnlineItem = new ExpandableDrawerItem()
                .withIdentifier(1).withName("Muabanonline.org")
                .withSelectable(false);

        SecondaryDrawerItem muaBanOnlineThreads = new SecondaryDrawerItem().withName("Threads")
                .withLevel(2)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Logger.i("muaBanOnlineItem clicked");
                        getSupportActionBar().setTitle("MuaBanOnline.org");
                        pagingObject.currentWebsite = "http://muabanonline.org/";
                        pagingObject.currentMethod = "getThreads";
                        pagingObject.bypassHashString = DefaultInstances.Default_API_Key;
                        pagingObject.setOnLoading(PagingObject.STATUS.Reset);
                        return false;
                    }
                });

        SecondaryDrawerItem muaBanOnlinePosts = new SecondaryDrawerItem().withName("Post")
                .withLevel(2)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Logger.i("muaBanOnlineItem clicked");
                        getSupportActionBar().setTitle("MuaBanOnline.org");
                        pagingObject.currentWebsite = "http://muabanonline.org/";
                        pagingObject.currentMethod = "getPosts";
                        pagingObject.bypassHashString = DefaultInstances.Default_API_Key;
                        pagingObject.setOnLoading(PagingObject.STATUS.Reset);
                        return false;
                    }
                });

        SecondaryDrawerItem muaBanOnlineViewPostedPost = new SecondaryDrawerItem().withName("Secrettttt!!!szz!!")
                .withLevel(2)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Logger.i("muaBanOnlineItem clicked");
                        getSupportActionBar().setTitle("MuaBanOnline.org");
                        pagingObject.currentUrl = "http://muabanonline.org/api.php?action=getThreads&hash=" + DefaultInstances.Default_API_Key + "&node_id=65";
                        pagingObject.currentWebsite = null;
                        pagingObject.currentMethod = null;
                        pagingObject.bypassHashString = null;
                        pagingObject.setOnLoading(PagingObject.STATUS.Reset);
                        return false;
                    }
                });

        muaBanOnlineItem.withSubItems(muaBanOnlineThreads);
        muaBanOnlineItem.withSubItems(muaBanOnlinePosts);
        muaBanOnlineItem.withSubItems(muaBanOnlineViewPostedPost);

        ///Thegioitinhte.com
        ExpandableDrawerItem thegioitinhteItem = new ExpandableDrawerItem().withIdentifier(1)
                .withName("Thegioitinhte.com").withSelectable(false);

        SecondaryDrawerItem thegioitinhteThreads = new SecondaryDrawerItem().withName("Threads")
                .withLevel(2)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Logger.i("Thegioitinhte clicked");
                        getSupportActionBar().setTitle("Thegioitinhte.com/Latest Threads");
                        pagingObject.currentWebsite = "http://thegioitinhte.com/";
                        pagingObject.currentMethod = "getThreads";
                        pagingObject.bypassHashString = DefaultInstances.Default_API_Key;
                        pagingObject.setOnLoading(PagingObject.STATUS.Reset);
                        return false;
                    }
                });

        SecondaryDrawerItem thegioitinhtePosts = new SecondaryDrawerItem().withName("Post")
                .withLevel(2)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Logger.i("Thegioitinhte clicked");
                        getSupportActionBar().setTitle("Thegioitinhte.org/Latest Post");
                        pagingObject.currentWebsite = "http://thegioitinhte.com/";
                        pagingObject.currentMethod = "getPosts";
                        pagingObject.bypassHashString = DefaultInstances.Default_API_Key;
                        pagingObject.setOnLoading(PagingObject.STATUS.Reset);
                        return false;
                    }
                });

        SecondaryDrawerItem thegioitinhteViewPostedPost = new SecondaryDrawerItem().withName("Secrettttt!!!szz!!")
                .withLevel(2)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Logger.i("muaBanOnlineItem clicked");
                        getSupportActionBar().setTitle("Thegioitinhte.org/View Secret");
                        pagingObject.currentUrl = "http://thegioitinhte.com/api.php?action=getThreads&hash=" + DefaultInstances.Default_API_Key + "&node_id=4";
                        pagingObject.currentWebsite = null;
                        pagingObject.currentMethod = null;
                        pagingObject.bypassHashString = null;
                        pagingObject.setOnLoading(PagingObject.STATUS.Reset);
                        return false;
                    }
                });

        thegioitinhteItem.withSubItems(thegioitinhteThreads);
        thegioitinhteItem.withSubItems(thegioitinhtePosts);
        thegioitinhteItem.withSubItems(thegioitinhteViewPostedPost);


        ///iproject
        PrimaryDrawerItem iProjectItem = new PrimaryDrawerItem().withIdentifier(1).withName("iProject.vn");
        iProjectItem.withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                Logger.i("iProject.vn clicked");
                getSupportActionBar().setTitle("iProject.vn");
                pagingObject.currentWebsite = "http://iproject.vn/";
                pagingObject.bypassHashString = DefaultInstances.Default_API_Key;
                pagingObject.currentMethod = "getThreads";
                pagingObject.setOnLoading(PagingObject.STATUS.Reset);
                return false;
            }
        });

        new DrawerBuilder().withActivity(this).withToolbar((Toolbar) findViewById(R.id.toolbar))
                .addDrawerItems(
                        muaBanOnlineItem,
                        new DividerDrawerItem(),
                        thegioitinhteItem,
                        new DividerDrawerItem(),
                        iProjectItem
                )
                .build();

    }

    /**
     * Load dữ liệu đã được xử lí vào adapter
     *
     * @param items
     */
    void loadData(ArrayList<ThreadObject> items) {
        Logger.i("loadData");

        //Logger.d("loadData|items.size = %d", items.size());
        adapter.addData(items);

        pagingObject.setOnLoading(PagingObject.STATUS.Success);

    }

    /**
     * Download dữ liệu từ api, pagingObject sẽ tính toán vị trí và trả về url cần thiết
     */
    void downloadData() {
        Logger.d("downloadData|onLoading:" + pagingObject.getOnLoading() + " , currentPage:" + pagingObject.getCurrentPageApi());
        if (pagingObject.getOnLoading() == PagingObject.STATUS.OnLoading) {
            return;
        }
        pagingObject.setOnLoading(PagingObject.STATUS.OnLoading);
        Downloader downloader = new Downloader(this);
        downloader.downloadJSON(pagingObject.getCurrentPageApi(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (response.has("threads")) {
                    try {
                        processData(response.getJSONArray("threads"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                        pagingObject.setOnLoading(PagingObject.STATUS.Fail);
                    }
                    // JSONParser.parseJson()
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pagingObject.setOnLoading(PagingObject.STATUS.Fail);
            }
        });
    }

    void processData(JSONArray postArr) throws JSONException {
        Logger.i("processData");
        ArrayList<ThreadObject> arrItems = new ArrayList<>();
        for (int i = 0; i < postArr.length(); i++) {
            String rawString = postArr.get(i).toString();
            //Logger.d(rawString);
            BaseObject baseObject = JSONParser.parseJson(rawString, JSONParser.JSON_OBJECT.THREAD);
            if (baseObject instanceof ThreadObject) {
                ThreadObject threadObject = (ThreadObject) baseObject;
                arrItems.add(threadObject);
                //Logger.d(postObject_xenforo.getName());
            }

        }
        loadData(arrItems);
    }
}
