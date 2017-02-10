package app.tabletplaza.tfa.layout.behaviors;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.orhanobut.logger.Logger;

/**
 * Tùy chỉnh lại AppBarLayout.Behavior dể điều khiển các layout animation liên quan đến ứng dụng
 * Created by SolbadguyKY on 06-Feb-17.
 */
public class MyAppbarBehavior extends AppBarLayout.Behavior {

    public MyAppbarBehavior() {
    }

    public MyAppbarBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, AppBarLayout child, View dependency) {
        //Logger.d(dependency);
        return super.layoutDependsOn(parent, child, dependency);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout parent, AppBarLayout child, View directTargetChild, View target, int nestedScrollAxes) {
        return super.onStartNestedScroll(parent, child, directTargetChild, target, nestedScrollAxes);
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout abl, View target) {
        super.onStopNestedScroll(coordinatorLayout, abl, target);
    }

    @Override
    public void setDragCallback(@Nullable DragCallback callback) {
        super.setDragCallback(callback);
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, AppBarLayout abl, int layoutDirection) {
        return super.onLayoutChild(parent, abl, layoutDirection);
    }

    @Override
    public boolean onMeasureChild(CoordinatorLayout parent, AppBarLayout child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        return super.onMeasureChild(parent, child, parentWidthMeasureSpec, widthUsed, parentHeightMeasureSpec, heightUsed);
    }

    @Override
    protected void layoutChild(CoordinatorLayout parent, AppBarLayout child, int layoutDirection) {
        super.layoutChild(parent, child, layoutDirection);
    }

    ///Nơi tiếp nhận sự thay đổi của DependencyView
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, AppBarLayout child, View dependency) {
        //Logger.d(dependency);
        return super.onDependentViewChanged(parent, child, dependency);
    }

    @Override
    public boolean onTouchEvent(CoordinatorLayout parent, AppBarLayout child, MotionEvent ev) {
        return super.onTouchEvent(parent, child, ev);
    }

    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, float velocityX, float velocityY, boolean consumed) {
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
    }

    @Override
    public Parcelable onSaveInstanceState(CoordinatorLayout parent, AppBarLayout abl) {
        return super.onSaveInstanceState(parent, abl);
    }

    @Override
    public void onRestoreInstanceState(CoordinatorLayout parent, AppBarLayout appBarLayout, Parcelable state) {
        super.onRestoreInstanceState(parent, appBarLayout, state);
    }

    @Override
    public boolean onInterceptTouchEvent(CoordinatorLayout parent, AppBarLayout child, MotionEvent ev) {
        return super.onInterceptTouchEvent(parent, child, ev);
    }
}
