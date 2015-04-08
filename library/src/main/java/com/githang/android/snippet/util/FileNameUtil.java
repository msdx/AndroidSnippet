package com.githang.android.snippet.util;

/**
 * 文件名工具.
 * @author huanghaohang msdx.android@qq.com
 * @version 0.1
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
