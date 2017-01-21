package app.tabletplaza.tfa.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import app.tabletplaza.tfa.R;

/**
 * Created by SolbadguyKY on 21-Jan-17.
 */

public class PostView extends RecyclerView.ViewHolder {

    public ImageView thumbnailView;
    public TextView titleView, descriptionView;

    public ImageView userAvatarView;
    public TextView usernameView, postDateView;

    public PostView(View itemView) {
        super(itemView);

        thumbnailView = (ImageView) itemView.findViewById(R.id.postView_imageView_Thumbnail);
        titleView = (TextView) itemView.findViewById(R.id.postView_textView_Title);
        descriptionView = (TextView) itemView.findViewById(R.id.postView_textView_Description);

        userAvatarView = (ImageView) itemView.findViewById(R.id.userProfile_imageView_ProfilePicture);
        usernameView = (TextView) itemView.findViewById(R.id.userProfile_textView_Username);
        postDateView = (TextView) itemView.findViewById(R.id.userProfile_textView_CustomString);
    }
}
