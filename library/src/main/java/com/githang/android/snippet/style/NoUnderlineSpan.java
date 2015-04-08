/*
 * Date: 14-9-4
 * Project: Access-Control-V2
 */
package com.githang.android.snippet.style;

import android.text.TextPaint;
import android.text.style.UnderlineSpan;

/**
 * 无下划线的Span
 * @author HaohangHuang msdx.android@qq.com
 * @version 0.1
 */
public class NoUnderlineSpan extends UnderlineSpan {

    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setColor(ds.linkColor);
        ds.setUnderlineText(false);
    }
}
