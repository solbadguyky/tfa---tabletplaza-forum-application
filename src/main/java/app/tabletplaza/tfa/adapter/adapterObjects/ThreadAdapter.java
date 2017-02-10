package app.tabletplaza.tfa.adapter.adapterObjects;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.orhanobut.logger.Logger;

import org.json.JSONObject;
import org.ocpsoft.prettytime.PrettyTime;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Locale;

import app.tabletplaza.tfa.R;
import app.tabletplaza.tfa.adapter.viewholder.PostView;
import app.tabletplaza.tfa.instances.DefaultInstances;
import app.tabletplaza.tfa.networkHelper.Requester;
import app.tabletplaza.tfa.objects.PagingObject;
import app.tabletplaza.tfa.objects.ThreadObject;
import app.tabletplaza.tfa.utilities.Devices;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.CropTransformation;

import static android.text.Html.FROM_HTML_SEPARATOR_LINE_BREAK_HEADING;

/**
 * Created by SolbadguyKY on 21-Jan-17.
 */

public class ThreadAdapter extends BaseQuickAdapter<ThreadObject, PostView> {

    private Context context;
    public PagingObject currentPagingObject;

    public boolean isEnableActionModul = false;

    public ThreadAdapter(int layoutResId) {
        super(layoutResId);

    }

    @Override
    protected PostView createBaseViewHolder(View view) {
        return new PostView(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void convert(final PostView viewHolder, ThreadObject item) {
        //get the context
        context = viewHolder.itemView.getContext();

        viewHolder.addOnClickListener(viewHolder.itemView.getId());

        //bind our data
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            viewHolder.titleView.setText(Html.fromHtml(item.getName(), FROM_HTML_SEPARATOR_LINE_BREAK_HEADING));
        } else {
            viewHolder.titleView.setText(Html.fromHtml(item.getName()));
        }

        viewHolder.descriptionView.setText(item.getUrl());
        viewHolder.usernameView.setText(item.getUserObject().getName());
        viewHolder.viewCountView.setText("" + item.getThreadViewCount() + " luot xem");
        viewHolder.replyView.setText("" + item.getThreadReplyCount() + " comment");

        ///Parse ngày tháng
        PrettyTime prettyTime = new PrettyTime();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            prettyTime.setLocale(Locale.forLanguageTag("vie"));
            viewHolder.replyView.setSupportAllCaps(true);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(item.getCreatedDate() * 1000);
        viewHolder.postDateView.setText(prettyTime.format(calendar.getTime()));

        ///Tải ảnh bài viết
        if (item.getImage() != null && !item.getImage().isEmpty()) {
            loadNormalImage(viewHolder.thumbnailView, item.getImage());
        } else {
            //Logger.d("CheckImage Tag");
            loadNullImage(viewHolder.thumbnailView);
        }

        if (item.getUserObject().getImage() != null && !item.getUserObject().getImage().isEmpty()) {
            loadUserImage(viewHolder.userAvatarView, item.getUserObject().getImage());
        }

        loadButtonBuilder(viewHolder.actionButtonView, item);
        if (isEnableActionModul) {
            viewHolder.actionButtonView.setEnabled(true);
            viewHolder.actionButtonView.setVisibility(View.VISIBLE);
        } else {
            viewHolder.actionButtonView.setEnabled(false);
            viewHolder.actionButtonView.setVisibility(View.GONE);
        }
    }

    private void loadNullImage(ImageView imageView) {
        Glide.with(context)
                .load(R.id.material_drawer_account_header_background)
                .into(imageView);
        imageView.getLayoutParams().height = Devices.getDefaultDevice(context).getSize(false) / 4;
    }

    private void loadNormalImage(final ImageView imageView, final String src) {
        Glide.with(context)
                .load(src)
                .bitmapTransform(new CropTransformation(context, imageView.getWidth(),
                        imageView.getHeight(), CropTransformation.CropType.TOP)
                )
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        loadNullImage(imageView);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model,
                                                   Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        imageView.getLayoutParams().height = Devices.getDefaultDevice(context).getSize(false) / 4;
                        return false;
                    }
                })
                .into(imageView);

    }

    private void loadUserImage(final ImageView imageView, final String src) {
        Glide.with(context)
                .load(src)
                .bitmapTransform(new CropCircleTransformation(context))
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        loadNullImage(imageView);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model,
                                                   Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        imageView.setBackgroundResource(android.R.color.transparent);

                        return false;
                    }
                })
                .into(imageView);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void loadButtonBuilder(BoomMenuButton buttonView, final ThreadObject item) {
        buttonView.clearBuilders();
        for (int i = 0; i < buttonView.getPiecePlaceEnum().pieceNumber(); i++) {
            HamButton.Builder builder = new HamButton.Builder();
            switch (i) {
                case 0:
                    builder.normalText(context.getResources().getString(R.string.sendTo, "Thegioitinhte.com"));
                    builder.listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            Toast.makeText(context, context.getResources().getString(R.string.sendTo, "Thegioitinhte.com"),
                                    Toast.LENGTH_SHORT).show();
                            try {
                                requestPostTo2(item);
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    builder.normalColor(context.getResources().getColor(R.color.material_deep_purple_800));
                    break;
                case 1:
                    builder.normalText(context.getResources().getString(R.string.sendTo, "Muabanonline.org"));
                    builder.listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            Toast.makeText(context, context.getResources().getString(R.string.sendTo, "Muabanonline.org"),
                                    Toast.LENGTH_SHORT).show();
                            try {
                                requestPost(item);
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    builder.normalColor(context.getResources().getColor(R.color.material_amber_900));
                    break;
            }
            buttonView.addBuilder(builder);
        }

    }

    /**
     * Gửi bài viết đến mua bán online
     *
     * @param item
     */
    private void requestPost(ThreadObject item) throws UnsupportedEncodingException {
        final Requester requester = new Requester(context);
        String convertString = String.format("http://muabanonline.org/api.php?action=createThread&hash=" + DefaultInstances.Default_API_Key + "&node_id=65&title=%s&grab_as=%s&message=%s",
                URLEncoder.encode(item.getName(), "utf-8"),
                URLEncoder.encode(item.getUserObject().getName(), "utf-8"),
                URLEncoder.encode(item.getPostDescription(), "utf-8"));

        //Logger.d(convertString);
        requester.sendPost(item.getPostDescription(), convertString, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Logger.d(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                Logger.d(error);
            }
        });
        /*downloader.downloadJSON(convertApiLink, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        BaseObject baseObject = JSONParser.parseJson(response.toString(), JSONParser.JSON_OBJECT.THREAD);
                        if (baseObject instanceof ThreadObject) {
                            ThreadObject threadObject =
                        }

                    }
                    // JSONParser.parseJson()
                }
                , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });*/
    }

    /**
     * Gửi bài viết đến Muabanonline.org
     *
     * @param item
     */
    private void requestPostTo2(ThreadObject item) throws UnsupportedEncodingException {
        final Requester requester = new Requester(context);
        String convertString = String.format("http://thegioitinhte.com/api.php?action=createThread&hash=" + DefaultInstances.Default_API_Key + "&node_id=4&title=%s&grab_as=%s&message=%s",
                URLEncoder.encode(item.getName(), "utf-8"),
                "admin",
                URLEncoder.encode(item.getPostDescription(), "utf-8"));

        requester.sendPost(item.getPostDescription(), convertString, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Logger.d(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                Logger.d(error);
            }
        });
    }

    /**
     * Xóa toàn bộ dữ liệu
     */
    public void clearData() {
        for (int i = 0; i < getItemCount(); i++) {
            super.remove(i);
        }
    }


    @Override
    public int getItemCount() {
        //Logger.d(super.getItemCount());
        return super.getItemCount();
    }

    @Override
    public void addData(ThreadObject data) {
        super.addData(data);
    }
}