/*
 * Date: 14-7-18
 * Project: Access-Control-V2
 */
package com.githang.android.snippet.util;

/**
 * 文件名工具.
 * Author: msdx (645079761@qq.com)
 * Time: 14-7-18 上午10:37
 */
public class FileNameUtil {

    /**
     * 过滤文件名中的非法字符,并返回新的名字.
     *
     * @param fileName 原文件名.
     * @return 过滤后的结果.
     */
    public static final String filterFileName(String fileName) {
        return fileName.replaceAll("[^a-zA-Z0-9]", "").replaceAll("\\s+", "_");
    }
}
