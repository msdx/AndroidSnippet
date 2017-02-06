package com.githang.android.snippet.graphics;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;

/**
 * @author 黄浩杭 (huanghaohang@parkingwang.com)
 * @since 2016-12-26
 */
public class BadgeDrawable extends Drawable {
    private Drawable mDrawable;
    private boolean mShowBadge;
    private Paint mPaint;
    private int mRadius;
    private int mGravity = Gravity.CENTER;

    public BadgeDrawable(Drawable origin) {
        mDrawable = origin;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    }

    public void setShowBadge(boolean showBadge) {
        mShowBadge = showBadge;
        invalidateSelf();
    }

    public void setRadius(int radius) {
        this.mRadius = radius;
    }

    public void setColor(@ColorInt int color) {
        mPaint.setColor(color);
    }

    public void setGravity(int gravity) {
        this.mGravity = gravity;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        mDrawable.draw(canvas);
        if (mShowBadge) {
            int cx = getBounds().right;
            int cy = getBounds().top;
            if ((Gravity.LEFT & mGravity) == Gravity.LEFT) {
                cx -= mRadius;
            } else if ((Gravity.RIGHT & mGravity) == Gravity.RIGHT) {
                cx += mRadius;
            }
            if ((Gravity.TOP & mGravity) == Gravity.TOP) {
                cy -= mRadius;
            } else if ((Gravity.BOTTOM & mGravity) == Gravity.BOTTOM) {
                cy += mRadius;
            }
            canvas.drawCircle(cx, cy, mRadius, mPaint);
        }
    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {
        mDrawable.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mDrawable.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return mDrawable.getOpacity();
    }

    @Override
    public int getIntrinsicHeight() {
        return mDrawable.getIntrinsicHeight();
    }

    @Override
    public int getIntrinsicWidth() {
        return mDrawable.getIntrinsicWidth();
    }

    @Override
    public void setBounds(@NonNull Rect bounds) {
        super.setBounds(bounds);
        mDrawable.setBounds(bounds);
    }

    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);
        mDrawable.setBounds(left, top, right, bottom);
    }

    public static BadgeDrawable wrap(Drawable drawable) {
        if (drawable instanceof BadgeDrawable) {
            return (BadgeDrawable) drawable;
        }
        return new BadgeDrawable(drawable);
    }
}
