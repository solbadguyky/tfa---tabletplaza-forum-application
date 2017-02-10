package app.tabletplaza.tfa.layout.behaviors;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by SolbadguyKY on 06-Feb-17.
 */

public class PostDetailHeaderBehavior extends CoordinatorLayout.Behavior<ImageView> {

    public PostDetailHeaderBehavior() {
    }

    public PostDetailHeaderBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, ImageView child, View dependency) {

        return super.onDependentViewChanged(parent, child, dependency);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, ImageView child, View dependency) {
        return super.layoutDependsOn(parent, child, dependency);
    }
}

