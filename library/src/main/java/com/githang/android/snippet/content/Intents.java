package com.githang.android.snippet.content;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;

/**
 * Intent 工具类。
 *
 * @author msdx.android@qq.com
 * @version 2017-12-15 0.6.5
 * @since 2015-06-18
 */
public class Intents {

    /**
     * 跳转到打电话的界面的Intent。
     *
     * @param phoneNumber 要拨打的号码
     * @return 打电话的Intent
     */
    public static Intent call(String phoneNumber) {
        final Uri uri = Uri.parse("tel:" + phoneNumber);
        return new Intent(Intent.ACTION_DIAL, uri);
    }

    /**
     * 跳转到指定网页的 Intent
     *
     * @param url 网页地址
     * @return 跳转的 Intent
     * @since 0.6.5
     */
    public static Intent website(String url) {
        return new Intent(Intent.ACTION_VIEW)
                .setData(Uri.parse(url));
    }

    /**
     * 定位设置的Intent
     *
     * @return 返回定位设置的Intent
     */
    public static Intent locationSetting() {
        return new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
    }

    /**
     * 获取 App 设置的 Intent
     *
     * @param context 要跳转的 app
     * @return App 设置的 Intent
     * @since 0.6.4
     */
    public static Intent appSetting(@NonNull Context context) {
        return new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                .setData(Uri.fromParts("package", context.getPackageName(), null));
    }
}
