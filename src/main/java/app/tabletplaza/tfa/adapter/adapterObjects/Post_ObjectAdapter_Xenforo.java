package app.tabletplaza.tfa.adapter.adapterObjects;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.mikepenz.fastadapter.items.AbstractItem;
import com.orhanobut.logger.Logger;

import org.json.JSONObject;
import org.ocpsoft.prettytime.PrettyTime;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import app.tabletplaza.tfa.R;
import app.tabletplaza.tfa.adapter.viewholder.PostView;
import app.tabletplaza.tfa.instances.DefaultInstances;
import app.tabletplaza.tfa.networkHelper.Downloader;
import app.tabletplaza.tfa.objects.PostObject_Xenforo;
import jp.wasabeef.glide.transformations.CropTransformation;

import static android.text.Html.FROM_HTML_SEPARATOR_LINE_BREAK_HEADING;

/**
 * Created by SolbadguyKY on 21-Jan-17.
 */

public class Post_ObjectAdapter_Xenforo extends AbstractItem<Post_ObjectAdapter_Xenforo, PostView> {

    private PostObject_Xenforo postObject;

    //The unique ID for this type of item
    @Override
    public int getType() {
        return R.id.fastadapter_post_item_id;
    }

    //The layout to be used for this type of item
    @Override
    public int getLayoutRes() {
        return R.layout.post_view;
    }

    //The logic to bind your data to the view
    @Override
    public void bindView(final PostView viewHolder, List<Object> payloads) {
        //call super so the selection is already handled for you
        super.bindView(viewHolder, payloads);
        //get the context
        Context ctx = viewHolder.itemView.getContext();

        //bind our data
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            viewHolder.titleView.setText(Html.fromHtml(postObject.getName(), FROM_HTML_SEPARATOR_LINE_BREAK_HEADING));
        } else {
            viewHolder.titleView.setText(Html.fromHtml(postObject.getName()));
        }

        viewHolder.descriptionView.setText(postObject.getUrl());
        viewHolder.usernameView.setText(postObject.getUsername());
        viewHolder.viewCountView.setText(postObject.getViewCount() + " luot xem");
        viewHolder.replyView.setText("" + postObject.getReplyCount() + " comment");
        viewHolder.likeView.setText("" + postObject.getLikeCount() + " like");

        PrettyTime prettyTime = new PrettyTime();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            prettyTime.setLocale(Locale.forLanguageTag("vie"));
            viewHolder.replyView.setSupportAllCaps(true);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(postObject.getCreatedDate() * 1000);
        viewHolder.postDateView.setText(prettyTime.format(calendar.getTime()));

        if (postObject.getImage() != null && !postObject.getImage().isEmpty()) {
            //GlideUtilities.loadImageToImageView(ctx,postObject.getImage(),viewHolder.thumbnailView);
            Glide.with(ctx)
                    .load(postObject.getImage())
                    .bitmapTransform(new CropTransformation(ctx, viewHolder.thumbnailView.getWidth(),
                            viewHolder.thumbnailView.getHeight(), CropTransformation.CropType.TOP)
                    )
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model,
                                                       Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            viewHolder.thumbnailView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            return false;
                        }
                    })
                    .into(viewHolder.thumbnailView);

        } else {
            Logger.d("CheckImage Tag");

            ///Nếu chưa có ảnh thì sẽ get ảnh từ downloader
            String downloadExactlyThreadUrl = String.format("http://muabanonline.org/api.php?action=getThread&hash=" + DefaultInstances.Default_API_Key + "&value=%d", postObject.getId());
            String jsonTag = "postThumbnail";
            Downloader.downloadMissingData(ctx, postObject.getSeriesNumber(), jsonTag, downloadExactlyThreadUrl, new RequestQueue.RequestFinishedListener<JSONObject>() {
                @Override
                public void onRequestFinished(Request<JSONObject> request) {

                }
            });
        }
    }

    //reset the view here (this is an optional method, but recommended)
    @Override
    public void unbindView(PostView holder) {
        super.unbindView(holder);
        holder.titleView.setText(null);
        holder.descriptionView.setText(null);
        holder.thumbnailView.setImageDrawable(null);

    }

    public void setPostObject(PostObject_Xenforo postObject) {
        this.postObject = postObject;
    }
}