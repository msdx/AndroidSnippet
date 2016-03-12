package com.githang.android.snippet.util;

import android.text.TextUtils;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;

/**
 * @author Geek_Soledad (msdx.android@qq.com)
 * @version 2016-01-17
 * @since 2016-01-17
 */
public class IOUtils {
    public static void close(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean deleteFile(String path) {
        return !TextUtils.isEmpty(path) && new File(path).delete();
    }
}
