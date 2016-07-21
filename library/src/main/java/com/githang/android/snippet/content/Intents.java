package com.githang.android.snippet.content;

import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

/**
 * Intent 工具类。
 *
 * @author msdx.android@qq.com
 * @since 2015-06-18
 */
public class Intents {

    /**
     * 跳转到打电话的界面的Intent。
     *
     * @param phoneNumber 要拨打的号码
     * @return 打电话的Intent
     */
    public static final Intent call(String phoneNumber) {
        Uri uri = Uri.parse("tel:" + phoneNumber);
        return new Intent(Intent.ACTION_DIAL, uri);
    }

    /**
     * 定位设置的Intent
     * @return 返回定位设置的Intent
     */
    public static final Intent locationSetting() {
        return new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
    }
}
