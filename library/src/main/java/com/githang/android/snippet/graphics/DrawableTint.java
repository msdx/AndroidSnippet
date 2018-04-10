package com.githang.android.snippet.graphics;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.graphics.drawable.TintAwareDrawable;

/**
 * @author Geek_Soledad (msdx.android@qq.com)
 * @since 0.6.4
 */
public class DrawableTint {

    public static Drawable tint(Drawable drawable, @ColorInt int tint) {
        drawable = DrawableCompat.wrap(drawable);
        DrawableTint.setTintMode(drawable, PorterDuff.Mode.MULTIPLY);
        drawable = drawable.mutate();
        DrawableCompat.setTint(drawable, tint);
        return drawable;
    }

    /**
     * 对指定 Drawable 进行着色
     *
     * @param drawable 需要着色的 drawable
     * @param tint     状态颜色列表
     * @return 着色后的 drawable
     */
    public static Drawable tint(Drawable drawable, ColorStateList tint) {
        drawable = DrawableCompat.wrap(drawable);
        DrawableTint.setTintMode(drawable, PorterDuff.Mode.MULTIPLY);
        drawable = drawable.mutate();
        DrawableCompat.setTintList(drawable, tint);
        return drawable;
    }

    /**
     * 这里使用的是TintAwareDrawable的setTintMode方法
     *
     * @param drawable 需要着色的drawable
     * @param mode     PorterDuff.Mode
     */
    @SuppressLint("RestrictedApi")
    private static void setTintMode(Drawable drawable, PorterDuff.Mode mode) {
        if (drawable instanceof TintAwareDrawable) {
            ((TintAwareDrawable) drawable).setTintMode(mode);
        } else {
            DrawableCompat.setTintMode(drawable, mode);
        }
    }
}
