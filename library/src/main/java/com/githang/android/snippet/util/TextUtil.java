package com.githang.android.snippet.util;

/**
 * 文本处理工具类
 *
 * @author msdx.android@qq.com
 * @version 2016-02-02
 * @since 2016-02-02
 */
public class TextUtil {
    /**
     * 非空字符串拼接。
     *
     * @param delimiter 拼接的字符
     * @param texts     待拼接的字符串。如果字符串为null或""则会被跳过，不进行拼接。
     * @return 返回拼接后的结果。
     * @since 0.6.3
     */
    public static String concatNonEmpty(String delimiter, String... texts) {
        StringBuilder sb = new StringBuilder();
        final int size = texts.length;
        boolean firstTime = true;
        for (int i = 0; i < size; i++) {
            String text = texts[i];
            if (text != null && text.length() != 0) {
                if (firstTime) {
                    firstTime = false;
                } else {
                    sb.append(delimiter);
                }
                sb.append(text);
            }
        }
        return sb.toString();
    }

    /**
     * byte[]数组转换为16进制的字符串。
     *
     * @param data 要转换的字节数组。
     * @return 转换后的结果。
     */
    public static String byteArrayToHexString(byte[] data) {
        StringBuilder sb = new StringBuilder(data.length * 2);
        for (byte b : data) {
            int v = b & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString();
    }

    /**
     * 16进制表示的字符串转换为字节数组。
     *
     * @param s 16进制表示的字符串
     * @return byte[] 字节数组
     */
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] d = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            // 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个进制字节
            d[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(
                    s.charAt(i + 1), 16));
        }
        return d;
    }
}
