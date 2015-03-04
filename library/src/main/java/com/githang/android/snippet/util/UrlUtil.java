/*
 * Time: 15-3-4 下午2:17
 * Project: AndroidSnippet
 */
package com.githang.android.snippet.util;

import java.text.MessageFormat;
import java.util.Locale;

/**
 * URL工具
 * @author Haohang Huang (645079761@qq.com)
 */
public class UrlUtil {
    /**
     * 获取一个http://HOST:PORT/ 格式的URL
     * @param host 指定的主机
     * @param port 指定的端口
     * @return 返回一个http://HOST:PORT/ 格式的URL
     */
    public static String http(String host, String port) {
        String lowerCase = host.toLowerCase(Locale.getDefault());
        if (lowerCase.startsWith("http://") && !lowerCase.startsWith("https://")) {
            return MessageFormat.format("{0}:{1}/", host, port);
        } else {
            return MessageFormat.format("http://{0}:{1}/", host, port);
        }
    }
}
