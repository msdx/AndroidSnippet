/*
 * Copyright (c) 2017. Xi'an iRain IOT Technology service CO., Ltd (ShenZhen). All Rights Reserved.
 */
package com.githang.android.snippet.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.githang.android.snippet.R;

/**
 * @author Geek_Soledad (msdx.android@qq.com)
 * @since 2017-04-27
 */
public class PaddingWeightLinearLayout extends LinearLayout {
    private final int mTopWeight;
    private final int mBottomWeight;
    private final int mLeftWeight;
    private final int mRightWeight;

    public PaddingWeightLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.PaddingWeightLinearLayout);
        mTopWeight = ta.getInteger(R.styleable.PaddingWeightLinearLayout_ppTopPadding, 0);
        mBottomWeight = ta.getInteger(R.styleable.PaddingWeightLinearLayout_ppBottomPadding, 0);
        mLeftWeight = ta.getInteger(R.styleable.PaddingWeightLinearLayout_ppLeftPadding, 0);
        mRightWeight = ta.getInteger(R.styleable.PaddingWeightLinearLayout_ppRightPadding, 0);
        ta.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int layoutWidth = MeasureSpec.getSize(widthMeasureSpec);
        int layoutHeight = MeasureSpec.getSize(heightMeasureSpec);

        int widthSpec = MeasureSpec.makeMeasureSpec(widthMeasureSpec, MeasureSpec.AT_MOST);
        int heightSpec = MeasureSpec.makeMeasureSpec(heightMeasureSpec, MeasureSpec.AT_MOST);

        int childCount = getChildCount();
        int totalWidth = 0;
        int totalHeight = 0;
        int maxWidth = 0;
        int maxHeight = 0;

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            child.measure(widthSpec, heightSpec);
            LayoutParams params = (LayoutParams) child.getLayoutParams();
            int width = child.getMeasuredWidth() + params.leftMargin + params.rightMargin;
            int height = child.getMeasuredHeight() + params.topMargin + params.bottomMargin;
            totalWidth += width;
            totalHeight += height;
            if (width > maxWidth) {
                maxWidth = width;
            }
            if (height > maxHeight) {
                maxHeight = height;
            }
        }

        int spaceHorizontal;
        int spaceVertical;
        if (getOrientation() == VERTICAL) {
            spaceHorizontal = layoutWidth - maxWidth;
            spaceVertical = layoutHeight - totalHeight;
        } else {
            spaceHorizontal = layoutWidth - totalWidth;
            spaceVertical = layoutHeight - maxHeight;
        }
        if (spaceHorizontal < 0) {
            spaceHorizontal = 0;
        }
        if (spaceVertical < 0) {
            spaceVertical = 0;
        }

        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        int horizontalWeight = mLeftWeight + mRightWeight;
        if (spaceHorizontal > 0 && horizontalWeight > 0) {
            paddingLeft = mLeftWeight * spaceHorizontal / horizontalWeight;
            paddingRight = spaceHorizontal - paddingLeft;
        }

        int verticalWeight = mTopWeight + mBottomWeight;
        if (spaceVertical > 0 && verticalWeight > 0) {
            paddingTop = mTopWeight * spaceVertical / verticalWeight;
            paddingBottom = spaceVertical - paddingTop;
        }

        setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
