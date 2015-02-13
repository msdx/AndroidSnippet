/*
 * Date: 14-8-18
 * Project: Access-Control-V2
 */
package com.githang.android.snippet.adapter;

import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

/**
 * 抽象的PagerAdapter实现类,封装了内容为View的公共操作.
 * Author: msdx (645079761@qq.com)
 * Time: 14-8-18 下午2:34
 */
public abstract class AbstractViewPagerAdapter extends PagerAdapter {
    protected SparseArray<View> mViews;

    public AbstractViewPagerAdapter() {
        mViews = new SparseArray<View>();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mViews.get(position);
        if (view == null) {
            view = newView(position);
            mViews.put(position, view);
        }
        container.addView(view);
        return view;
    }

    /**
     * 创建一个新视图.
     *
     * @param position Pager中的位置.
     * @return View
     */
    public abstract View newView(int position);

    /**
     * 通知将更新指定位置的view.
     *
     * @param position 指定的位置.
     */
    public void notifyUpdateView(int position) {
        View view = updateView(mViews.get(position), position);
        mViews.put(position, view);
        notifyDataSetChanged();
    }

    /**
     * 更新视图.
     *
     * @param view     旧的view.
     * @param position 位置.
     * @return 新的view, 并替换旧的view.
     */
    public View updateView(View view, int position) {
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViews.get(position));
        mViews.remove(position);
    }
}