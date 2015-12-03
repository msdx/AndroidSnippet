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
 * @author HaohangHuang msdx.android@qq.com
 * @version 0.6
 */
public class BaseListAdapter<T> extends BaseAdapter {
    private List<T> mData;
    private ItemCreator<T> mItemCreator;

    public BaseListAdapter(ItemCreator<T> creator) {
        this(new ArrayList<T>(), creator);
    }

    public BaseListAdapter(List<T> data, ItemCreator<T> creator) {
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
        final Holder holder;
        if (convertView == null) {
            holder = mItemCreator.createHolder(position, parent);
            convertView = holder.getView();
        } else {
            holder = (Holder) convertView.getTag();
        }
        mItemCreator.bindData(position, holder, getItem(position));
        return convertView;
    }

    public void update(List<T> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public static class Holder {
        private SparseArray<View> mHolderViews;
        private View mItem;

        public Holder(View view) {
            mHolderViews = new SparseArray<>();
            mItem = view;
            mItem.setTag(this);
        }

        public void hold(int... resIds) {
            for (int id : resIds) {
                mHolderViews.put(id, mItem.findViewById(id));
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

        public View getView() {
            return mItem;
        }
    }

    public interface ItemCreator<T> {

        Holder createHolder(int position, ViewGroup parent);

        /**
         * 设置列表里的视图内容
         *
         * @param position 在列表中的位置
         * @param holder   该位置对应的视图
         */
        void bindData(int position, Holder holder, T data);
    }
}
