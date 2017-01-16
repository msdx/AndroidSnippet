/*
 * Date: 14-9-4
 * Project: Access-Control-V2
 */
package com.githang.android.snippet.style;

import android.os.Parcel;
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

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {}

    public NoUnderlineSpan() {}

    protected NoUnderlineSpan(Parcel in) {super(in);}

    public static final Creator<NoUnderlineSpan> CREATOR = new Creator<NoUnderlineSpan>() {
        @Override
        public NoUnderlineSpan createFromParcel(Parcel source) {return new NoUnderlineSpan(source);}

        @Override
        public NoUnderlineSpan[] newArray(int size) {return new NoUnderlineSpan[size];}
    };
}
