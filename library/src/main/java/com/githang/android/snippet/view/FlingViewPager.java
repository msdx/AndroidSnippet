package com.githang.android.snippet.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * 松手后根据手势再判断是否需要滑动
 *
 * @author Geek_Soledad (msdx.android@qq.com)
 * @since 2017-03-25
 */
public class FlingViewPager extends ViewPager {

    private GestureDetector.OnGestureListener mOnGestureListener = new GestureDetector.SimpleOnGestureListener() {

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            final float distanceX = e2.getX() - e1.getX();
            final float distanceY = e2.getY() - e1.getY();
            final float absDistanceX = Math.abs(distanceX);
            final float absDistanceY = Math.abs(distanceY);
            final float absVelocityX = Math.abs(velocityX);
            final float absVelocityY = Math.abs(velocityY);

            if (absDistanceX < absDistanceY || absVelocityX < absVelocityY || distanceX * velocityX < 0) {
                return true;
            }

            moveToNearItem(velocityX > 0);
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if (Math.abs(distanceX) > Math.abs(distanceY)) {
                getParent().requestDisallowInterceptTouchEvent(true);
            }
            return false;
        }
    };

    private GestureDetector mGestureDetector;

    public FlingViewPager(Context context) {
        this(context, null);
    }

    public FlingViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        mGestureDetector = new GestureDetector(context, mOnGestureListener);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mGestureDetector.onTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }

    private void moveToNearItem(boolean next) {
        if (next) {
            setCurrentItem(getCurrentItem() - 1, true);
        } else {
            setCurrentItem(getCurrentItem() + 1, true);
        }
    }
}
