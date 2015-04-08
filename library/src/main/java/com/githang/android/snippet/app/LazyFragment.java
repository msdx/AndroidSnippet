/*
 * Date: 14-7-17
 * Project: Access-Control-V2
 */
package com.githang.android.snippet.app;

import android.support.v4.app.Fragment;

/**
 * 懒加载的Fragment.它只在显示时才调用其中的lazyLoad()方法.
 * @author HaohangHuang msdx.android@qq.com
 * @version 0.1
 */
public abstract class LazyFragment extends Fragment {
    protected boolean isVisible;

    /**
     * 在这里实现Fragment数据的缓加载.
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible() {
        lazyLoad();
    }

    protected abstract void lazyLoad();

    protected void onInvisible() {
    }
}
