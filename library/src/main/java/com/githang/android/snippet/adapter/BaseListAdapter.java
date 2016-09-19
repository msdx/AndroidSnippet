/*
 * Date: 14-11-21
 * Project: Access-Control-TV
 */
package com.githang.android.snippet.adapter;

import android.support.annotation.IdRes;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Checkable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 进行简单封装的列表适配器
 *
 * @author Geek_Soledad (msdx.android@qq.com)
 * @version 0.6.3
 */
public class BaseListAdapter<T, H extends BaseListAdapter.AbstractItemHolder> extends BaseAdapter {
    private List<T> mData;
    private ItemCreator<T, H> mItemCreator;

    public BaseListAdapter(ItemCreator<T, H > creator) {
        this(null, creator);
    }

    public BaseListAdapter(List<T> data, ItemCreator<T, H> creator) {
        mData = data;
        mItemCreator = creator;
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
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

    public void setData(List<T> data) {
        mData = data;
    }

    public void addData(List<T> data) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.addAll(data);
    }

    public static class DefaultHolder extends AbstractItemHolder {
        private SparseArray<View> mHolderViews;

        public DefaultHolder(View view) {
            super(view);
            mHolderViews = new SparseArray<>();
        }

        public void hold(int... resIds) {
            for (int id : resIds) {
                mHolderViews.put(id, itemView.findViewById(id));
            }
        }

        public <V> V get(int id) {
            return (V) mHolderViews.get(id);
        }

        public void setText(@IdRes int id, String text) {
            TextView textView = get(id);
            textView.setText(text);
        }

        public void setChecked(@IdRes int id, boolean checked) {
            Checkable button = get(id);
            button.setChecked(checked);
        }
    }
    public static abstract class AbstractItemHolder implements ItemHolder {
        public View itemView;

        public AbstractItemHolder(View itemView) {
            this.itemView = itemView;
            itemView.setTag(this);
        }
    }
}
