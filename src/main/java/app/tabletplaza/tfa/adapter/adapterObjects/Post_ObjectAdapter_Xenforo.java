package app.tabletplaza.tfa.adapter.adapterObjects;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.Calendar;
import java.util.List;

import app.tabletplaza.tfa.R;
import app.tabletplaza.tfa.adapter.viewholder.PostView;
import app.tabletplaza.tfa.objects.PostObject_Xenforo;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.CropTransformation;

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
        viewHolder.titleView.setText(postObject.getName());
        viewHolder.descriptionView.setText(postObject.getUrl());
        viewHolder.usernameView.setText(postObject.getUsername());

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(postObject.getCreatedDate());
        viewHolder.postDateView.setText(calendar.getTime().toString());

        if (postObject.getImage() != null) {
            Glide.with(ctx)
                    .load(postObject.getImage())
                    .bitmapTransform(new CropTransformation(ctx, viewHolder.thumbnailView.getWidth(),
                            viewHolder.thumbnailView.getHeight(), CropTransformation.CropType.TOP)
                    )
                    .crossFade()
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            viewHolder.thumbnailView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            return false;
                        }
                    })
                    .into(viewHolder.thumbnailView);

        }

        Glide.with(ctx)
                .load("https://scontent.fsgn5-2.fna.fbcdn.net/v/t1.0-9/15267651_1223457901054894_2182404773543390627_n.jpg?oh=44470a86cc3705b8c062004bf18b1dc1&oe=590AE5AE")
                .bitmapTransform(new CropCircleTransformation(ctx)).into(viewHolder.userAvatarView);

    }

    //reset the view here (this is an optional method, but recommended)
    @Override
    public void unbindView(PostView holder) {
        super.unbindView(holder);
        holder.titleView.setText(null);
        holder.descriptionView.setText(null);
    }

    public void setPostObject(PostObject_Xenforo postObject) {
        this.postObject = postObject;
    }
}