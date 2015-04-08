/*
 * Date: 14-8-18
 * Project: Access-Control-V2
 */
package com.githang.android.snippet.util;

import android.content.Context;

/**
 * 与View有关的工具类.
 * @author haohang msdx.android@qq.com
 * @version 0.1
 */
public class ViewUtil {
    /**
     * 获取屏幕宽度.
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * dp转px的工具类.
     *
     * @param context
     * @param dp
     * @return
     */
    public static int dip2px(Context context, int dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
