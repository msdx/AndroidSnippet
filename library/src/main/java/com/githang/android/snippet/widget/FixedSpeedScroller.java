/*
 * Date: 15-1-15
 * Project: park-here-android
 */
package com.githang.android.snippet.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.widget.Scroller;

import java.lang.reflect.Field;

/**
 * 用于处理ViewPager的滑动速度.
 *
 * @author HaohangHuang msdx.android@qq.com
 * @version 0.2
 */
public class FixedSpeedScroller extends Scroller {

    private int mDuration = 250;

    public int getmDuration() {
        return mDuration;
    }

    public void setDuration(int mDuration) {
        this.mDuration = mDuration;
    }

    public FixedSpeedScroller(Context context) {
        super(context);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    public void setToViewPager(ViewPager viewPager) {
        try {
            Field mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            mScroller.set(viewPager, this);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}