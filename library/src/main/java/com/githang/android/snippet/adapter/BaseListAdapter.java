/*
 * Date: 14-11-21
 * Project: Access-Control-TV
 */
package com.githang.android.snippet.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 进行简单封装的列表适配器
 *
 * @author Geek_Soledad (msdx.android@qq.com)
 * @version 0.6.3
 */
public class BaseListAdapter<T, H extends ItemHolder.AbstractItemHolder> extends BaseAdapter {
    private final List<T> mData;
    private final ItemCreator<T, H> mItemCreator;

    public BaseListAdapter(ItemCreator<T, H > creator) {
        this(new ArrayList<T>(), creator);
    }

    public BaseListAdapter(List<T> data, ItemCreator<T, H> creator) {
        mData = data == null ? new ArrayList<T>() : data;
        mItemCreator = creator;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final H holder;
        if (convertView == null) {
            holder = mItemCreator.createHolder(position, parent);
            convertView = holder.itemView;
        } else {
            holder = (H) convertView.getTag();
        }
        mItemCreator.bindData(position, holder, getItem(position));
        return convertView;
    }

    public void update(List<T> data) {
        mData.clear();
        addData(data);
    }

    public void addData(List<T> data) {
        if (data != null) {
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }
}
