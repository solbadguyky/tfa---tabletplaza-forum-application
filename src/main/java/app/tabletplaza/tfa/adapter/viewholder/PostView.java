package app.tabletplaza.tfa.adapter.viewholder;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.nightonke.boommenu.BoomMenuButton;

import app.tabletplaza.tfa.R;
import app.tabletplaza.tfa.utilities.Devices;

/**
 * Created by SolbadguyKY on 21-Jan-17.
 */

public class PostView extends BaseViewHolder {

    //post detail
    public ImageView thumbnailView;
    public TextView titleView, descriptionView;

    ///user
    public ImageView userAvatarView;
    public TextView usernameView, postDateView;

    //interactions
    public AppCompatButton replyView, likeView;
    public AppCompatTextView viewCountView;

    ///BoombuttonMenu
    public BoomMenuButton actionButtonView;

    public PostView(View itemView) {
        super(itemView);

        thumbnailView = (ImageView) itemView.findViewById(R.id.postView_imageView_Thumbnail);
        titleView = (TextView) itemView.findViewById(R.id.postView_textView_Title);
        descriptionView = (TextView) itemView.findViewById(R.id.postView_textView_Description);

        userAvatarView = (ImageView) itemView.findViewById(R.id.userProfile_imageView_ProfilePicture);
        usernameView = (TextView) itemView.findViewById(R.id.userProfile_textView_Username);
        postDateView = (TextView) itemView.findViewById(R.id.userProfile_textView_CustomString);

        viewCountView = (AppCompatTextView) itemView.findViewById(R.id.postBottomBar_textView_ViewCount);
        replyView = (AppCompatButton) itemView.findViewById(R.id.postBottomBar_button_Reply);
        likeView = (AppCompatButton) itemView.findViewById(R.id.postBottomBar_button_Like);

        actionButtonView = (BoomMenuButton) itemView.findViewById(R.id.postBottomBar_boomMenuButton);

        //setupView(itemView.getContext());
    }

    private void setupView(Context context) {
        thumbnailView.setMaxHeight(Devices.getDefaultDevice(context).getSize(false) / 3);
        thumbnailView.setMinimumHeight(Devices.getDefaultDevice(context).getSize(false) / 4);
    }
}
