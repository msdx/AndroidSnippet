/*
 * Date: 14-8-18
 * Project: Access-Control-V2
 */
package com.githang.android.snippet.adapter;

import java.util.List;

/**
 * 抽象的PagerAdapter实现类,封装了内容为View,数据为List类型的适配器实现.
 * @author HaohangHuang msdx.android@qq.com
 * @version 0.1
 */
public abstract class AbstractPagerListAdapter<T> extends AbstractViewPagerAdapter {
    protected List<T> mData;

    public AbstractPagerListAdapter(List<T> data) {
        mData = data;
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    public T getItem(int position) {
        return mData.get(position);
    }
}