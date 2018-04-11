package com.githang.android.snippet.graphics;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.graphics.drawable.TintAwareDrawable;

/**
 * @author Geek_Soledad (msdx.android@qq.com)
 * @since 0.6.5
 */
public class DrawableTint {

    /**
     * Tint the drawable with the special mode and color
     *
     * @param drawable The drawable to be tint
     * @param mode     tint mode
     * @param tint     tint color
     * @return The tint drawable
     */
    public static Drawable tint(Drawable drawable, PorterDuff.Mode mode, @ColorInt int tint) {
        drawable = setTintModeAndMutate(drawable, mode);
        DrawableCompat.setTint(drawable, tint);
        return drawable;
    }

    /**
     * 对指定 Drawable 进行着色
     *
     * @param drawable 需要着色的 drawable
     * @param mode     The tint mode
     * @param tint     状态颜色列表
     * @return 着色后的 drawable
     */
    public static Drawable tint(Drawable drawable, PorterDuff.Mode mode, ColorStateList tint) {
        drawable = setTintModeAndMutate(drawable, mode);
        DrawableCompat.setTintList(drawable, tint);
        return drawable;
    }

    @NonNull
    private static Drawable setTintModeAndMutate(Drawable drawable, PorterDuff.Mode mode) {
        drawable = DrawableCompat.wrap(drawable);
        if (drawable instanceof TintAwareDrawable) {
            ((TintAwareDrawable) drawable).setTintMode(mode);
        } else {
            DrawableCompat.setTintMode(drawable, mode);
        }
        drawable = drawable.mutate();
        return drawable;
    }

}
