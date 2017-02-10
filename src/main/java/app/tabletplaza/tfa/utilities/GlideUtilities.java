package app.tabletplaza.tfa.utilities;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import jp.wasabeef.glide.transformations.CropTransformation;

/**
 * Created by SolbadguyKY on 06-Feb-17.
 */

public class GlideUtilities {

    /**
     * Load ảnh vào ImageView bằng thư viện Glide
     *
     * @param context
     * @param imageSrc
     * @param imageView
     */
    public static void loadImageToImageView(Context context, String imageSrc, final ImageView imageView) {
        Glide.with(context)
                .load(imageSrc)
                .bitmapTransform(new CropTransformation(context, imageView.getWidth(),
                        imageView.getHeight(), CropTransformation.CropType.TOP)
                )
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model,
                                                   Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        return false;
                    }
                })
                .into(imageView);
    }
}
