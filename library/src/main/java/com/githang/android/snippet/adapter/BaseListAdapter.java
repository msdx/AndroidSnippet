/*
 * Date: 14-11-21
 * Project: Access-Control-TV
 */
package com.githang.android.snippet.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Checkable;
import android.widget.TextView;

import java.util.List;

/**
 * 进行简单封装的列表适配器
 *
 * @author HaohangHuang msdx.android@qq.com
 * @version 0.5
 */
public abstract class BaseListAdapter<T> extends BaseAdapter {
    private Context mContext;
    private List<T> mData;
    private int mViewLayoutId;

    public BaseListAdapter(Context context, List<T> data, int viewLayoutId) {
        mContext = context;
        mData = data;
        mViewLayoutId = viewLayoutId;
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
        final Holder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, mViewLayoutId, null);
            holder = new Holder(convertView);
            holdView(convertView, holder);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        bindData(position, holder, getItem(position));
        return convertView;
    }

    protected abstract void holdView(View parent, Holder holder);

    /**
     * 设置列表里的视图内容
     *
     * @param position 在列表中的位置
     * @param holder   该位置对应的视图
     */
    protected abstract void bindData(int position, Holder holder, T data);

    public void update(List<T> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public static class Holder {
        private SparseArray<View> mHolderViews;
        private View mItem;

        Holder(View view) {
            mHolderViews = new SparseArray<>();
            mItem = view;
        }

        public void hold(int... resIds) {
            for (int id : resIds) {
                mHolderViews.put(id, mItem.findViewById(id));
            }
        }

        public <V> V get(int id) {
            return (V) mHolderViews.get(id);
        }

        public TextView holdText(int id, String text) {
            TextView textView = get(id);
            textView.setText(text);
            return textView;
        }

        public Checkable holdCheckable(int id, boolean checked) {
            Checkable button = get(id);
            button.setChecked(checked);
            return button;
        }
    }
}
