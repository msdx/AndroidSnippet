package com.githang.android.snippet.view;

import android.graphics.Rect;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewParent;

/**
 * 触摸委托工具类。
 *
 * @author msdx.android@qq.com
 * @version 2016-03-23
 * @since 2016-03-23
 */
public class TouchDelegates {
    /**
     * 扩大触摸面积
     *
     * @param additional 要增加的每个方向的面积
     * @param view       要扩大触摸面积的View
     */
    public static void extendTouchRange(final int additional, final View view) {
        view.post(new Runnable() {
            @Override
            public void run() {
                final Rect touchRect = new Rect();
                view.getHitRect(touchRect);
                touchRect.left -= additional;
                touchRect.right += additional;
                touchRect.top -= additional;
                touchRect.bottom += additional;
                TouchDelegate touchDelegate = new TouchDelegate(touchRect, view);
                ViewParent parent = view.getParent();
                if (parent instanceof View) {
                    ((View) parent).setTouchDelegate(touchDelegate);
                }
            }
        });
    }
}
