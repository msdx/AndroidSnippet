package com.githang.android.snippet.content.pm;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;

import com.githang.android.snippet.security.DigestUtil;
import com.githang.android.snippet.util.TextUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 计算并缓存签名摘要。
 *
 * @author huanghaohang (msdx.android@qq.com)
 * @version 2015-06-13
 * @since 2.5
 */
public class AppSign {

    public static String getSignDigest(Context context, String algorithm) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo pi = pm.getPackageInfo(context.getApplicationInfo().packageName, PackageManager.GET_SIGNATURES);
            Signature signature = pi.signatures[0];
            return DigestUtil.doDigest(algorithm, signature.toByteArray());
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
