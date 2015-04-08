/*
 * Date: 14-7-28
 * Project: Access-Control-V2
 */
package com.githang.android.snippet.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 静态的ViewPager,不能通过左右滑动的ViewPager.
 * @author HaohangHuang msdx.android@qq.com
 * @version 0.1
 */
public class StaticViewPager extends ViewPager {
    public StaticViewPager(Context context) {
        super(context);
    }

    public StaticViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public void setCurrentItem(int item) {
        this.setCurrentItem(item, false);
    }
}
