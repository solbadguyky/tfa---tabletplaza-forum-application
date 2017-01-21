package app.tabletplaza.tfa.adapter.adapterObjects;

import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.List;

import app.tabletplaza.tfa.R;
import app.tabletplaza.tfa.adapter.viewholder.PostView;
import app.tabletplaza.tfa.objects.PostObject_Xenforo;

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
    public void bindView(PostView viewHolder, List<Object> payloads) {
        //call super so the selection is already handled for you
        super.bindView(viewHolder, payloads);

        //bind our data
        //set the text for the name
        viewHolder.titleView.setText(postObject.getName());
        //set the text for the description or hide
        viewHolder.descriptionView.setText(postObject.getPostDescription());
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