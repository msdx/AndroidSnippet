/*
 * Copyright (c) 2018. Xi'an iRain IOT Technology service CO., Ltd (ShenZhen). All Rights Reserved.
 */

package com.githang.android.snippet.widget;

import android.content.res.Resources;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;

/**
 * PopupWindow 弹出兼容类
 *
 * @author Geek_Soledad (msdx.android@qq.com)
 * @since 2018-04-09 0.6.5
 */
public class PopupWindowCompat {

    public static void showAsDropDown(PopupWindow popup, View anchor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            final int[] location = new int[2];
            anchor.getLocationInWindow(location);
            final int offsetY = location[1] + anchor.getHeight();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
                int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
                popup.setHeight(screenHeight - offsetY);
            }
            popup.showAtLocation(anchor.getRootView(), Gravity.NO_GRAVITY, 0, offsetY);
        } else {
            popup.showAsDropDown(anchor);
        }
    }
}
