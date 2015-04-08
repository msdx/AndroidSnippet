/*
 * Date: 15-2-3
 * Project: parking-system-android-phone-v2
 */
package com.githang.android.snippet.widget;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

/**
 * 字体自适应大小的TextView.
 * Copy from :http://stackoverflow.com/questions/2617266/how-to-adjust-text-font-size-to-fit-textview
 * <br/>
 * Modified by Haohang Huang.
 * @author HaohangHuang msdx.android@qq.com
 * @version 0.3
 */
public class FontFitTextView extends TextView {
    //Attributes
    private Paint mTestPaint;

    public FontFitTextView(Context context) {
        super(context);
        initialise();
    }

    public FontFitTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialise();
    }

    private void initialise() {
        mTestPaint = new Paint();
        mTestPaint.set(this.getPaint());
        //max size defaults to the initially specified text size unless it is too small
    }

    /* Re size the font so the specified text fits in the text box
     * assuming the text box is the specified width.
     */
    private void refitText(String text, int textWidth) {
        if (textWidth <= 0)
            return;
        int targetWidth = textWidth - this.getPaddingLeft() - this.getPaddingRight();
        int hi = 100;
        int lo = 8;

        int currentSize = (int) getPaint().getTextSize();
        int height = getMeasuredHeight();

        mTestPaint.setTextSize(currentSize);
        Paint.FontMetricsInt fm = mTestPaint.getFontMetricsInt();
        if (mTestPaint.measureText(text) >= targetWidth || (fm.descent - fm.top) > height)
            hi = currentSize; // too big
        else
            lo = currentSize; // too small

        int size;
        while ((hi - lo) > 1) {
             size = (hi + lo) / 2;
            mTestPaint.setTextSize(size);
            fm = mTestPaint.getFontMetricsInt();
            if (mTestPaint.measureText(text) >= targetWidth || (fm.descent - fm.top) > height)
                hi = size; // too big
            else
                lo = size; // too small
        }
        // Use lo so that we undershoot rather than overshoot
        this.setTextSize(TypedValue.COMPLEX_UNIT_PX, lo);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        int height = getMeasuredHeight();
        this.setMeasuredDimension(parentWidth, height);
        refitText(this.getText().toString(), parentWidth);
    }

    @Override
    protected void onTextChanged(final CharSequence text, final int start, final int before, final int after) {
        refitText(text.toString(), this.getWidth());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (w != oldw) {
            refitText(this.getText().toString(), w);
        }
    }
}
