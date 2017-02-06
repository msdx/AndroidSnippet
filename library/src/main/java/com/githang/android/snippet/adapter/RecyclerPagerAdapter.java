/*
 * Copyright (c) 2015. Xi'an iRain IOT Technology Service CO.,Ltd. All Rights Reserved.
 */

package com.githang.android.snippet.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 用于列表条目的快速Adapter,复用View
 *
 * @author 黄浩杭 (huanghaohang@parkingwang.com)
 * @version 4.0 2016-01-25
 * @since 4.0 2016-01-25
 */
public class RecyclerPagerAdapter<T, H extends ItemHolder.AbstractItemHolder> extends PagerAdapter {
    private List<T> mData;
    private ItemCreator<T, H> mViewCreator;
    private Stack<View> mRecyclers = new Stack<>();

    public RecyclerPagerAdapter(ItemCreator<T, H> delegate) {
        this.mViewCreator = delegate;
        this.mData = new ArrayList<>();
    }

    public void update(List<T> data) {
        mData.clear();
        if (data != null) {
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final T data = mData.get(position);
        final View view;
        final H holder;
        if (mRecyclers.isEmpty()) {
            holder = mViewCreator.createHolder(position, container);
            view = holder.itemView;
            view.setTag(holder);
        } else {
            view = mRecyclers.pop();
            holder = (H) view.getTag();
        }
        container.addView(view);
        mViewCreator.bindData(position, holder, data);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (object instanceof View) {
            View view = (View) object;
            container.removeView(view);
            mRecyclers.push(view);
        }
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    public T getObject(int position) {
        return mData.get(position);
    }

    public int getPosition(T object) {
        return mData.indexOf(object);
    }

    public List<T> getData() {
        return mData;
    }
}