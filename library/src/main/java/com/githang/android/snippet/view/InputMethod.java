package com.githang.android.snippet.view;

import android.content.Context;
import android.os.IBinder;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * 显示或隐藏输入法
 *
 * @author msdx.android@qq.com
 * @version 2016-07-27
 * @since 2016-07-27
 */

public class InputMethod {

    /**
     * 隐藏输入法
     * @param context 上下文对象
     * @param windowToken 该请求的window的token
     */
    public static void hideSoftInput(Context context, IBinder windowToken) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(windowToken, 0);
    }

    /**
     * 显示输入法
     * @param context 上下文对象
     * @param view 当前获取到焦点的View，用于接收软键盘输入
     */
    public static void showSoftInput(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }
}
