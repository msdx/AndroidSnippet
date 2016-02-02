package com.githang.android.snippet.util;

/**
 * 文本处理工具类
 *
 * @author 黄浩杭 (huanghaohang@parkingwang.com)
 * @version 2016-02-02
 * @since 2016-02-02
 */
public class TextUtil {
    /**
     * 字符串拼接。
     *
     * @param texts 待拼接的字符串。如果字符串为null或""则会被跳过，不进行拼接。
     * @return 返回拼接后的结果。
     */
    public static String concat(String... texts) {
        if (texts.length == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (String text : texts) {
            if (text != null && text.length() > 0) {
                sb.append(text);
            }
        }
        return sb.toString();
    }
}
