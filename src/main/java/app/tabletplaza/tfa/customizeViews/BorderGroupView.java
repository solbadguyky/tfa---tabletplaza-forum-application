package app.tabletplaza.tfa.customizeViews;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Borderview để hiển thị bài đăng được đóng khung trong Post/Thread View
 * Created by SolbadguyKY on 10-Feb-17.
 */

public class BorderGroupView extends ViewGroup {

    public BorderGroupView(Context context) {
        super(context);
    }

    public BorderGroupView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BorderGroupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
