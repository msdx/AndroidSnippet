package com.githang.android.snippet.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Checkable;
import android.widget.FrameLayout;

import java.util.List;

/**
 * 可选择的ListAdapter.
 *
 * @author msdx.android@qq.com
 * @version 0.4 15-5-13
 */
public class ChoiceListAdapter<T, H extends ChoiceListAdapter.AbstractChoiceView> extends BaseAdapter {
    private List<T> mData;
    private ItemCreator<T, H> mItemCreator;

    public ChoiceListAdapter(ItemCreator<T, H> creator) {
        this(null, creator);
    }

    /**
     * @param data    数据源
     * @param creator
     */
    public ChoiceListAdapter(List<T> data, ItemCreator<T, H> creator) {
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
        final H choiceHolderView;
        if (convertView == null) {
            choiceHolderView = mItemCreator.createHolder(position, parent);
            convertView = choiceHolderView;
        } else {
            choiceHolderView = (H) convertView;
        }
        mItemCreator.bindData(position, choiceHolderView, mData.get(position));
        return convertView;
    }

    public void updateData(List<T> data) {
        mData = data;
    }

    public List<T> getData() {
        return mData;
    }

    /**
     * 绑定Checkable控件的Layout.
     */
    public static abstract class AbstractChoiceView extends FrameLayout implements Checkable, ItemHolder {
        private Checkable[] mCheckViews;
        private boolean mChecked;

        protected AbstractChoiceView(Context context) {
            super(context);
        }

        public void setContentView(View view) {
            removeAllViews();
            addView(view);
        }

        public void setChoiceId(int... choiceId) {
            mCheckViews = new Checkable[choiceId.length];
            for (int i = 0; i < choiceId.length; i++) {
                mCheckViews[i] = (Checkable) findViewById(choiceId[i]);
            }
        }

        @Override
        public void setChecked(boolean checked) {
            for (Checkable checkable : mCheckViews) {
                checkable.setChecked(checked);
            }
            mChecked = checked;
        }

        @Override
        public boolean isChecked() {
            return mChecked;
        }

        @Override
        public void toggle() {
            setChecked(!mChecked);
        }
    }

    public static class DefaultChoiceView extends AbstractChoiceView {
        private SparseArray<View> mHolderViews;

        public DefaultChoiceView(Context context, View view) {
            super(context);
            setContentView(view);
            mHolderViews = new SparseArray<>();
        }

        /**
         * 持有子view
         *
         * @param id
         */
        public void hold(int id) {
            mHolderViews.put(id, findViewById(id));
        }

        /**
         * 获取子view。
         *
         * @param id
         * @param <V>
         * @return 返回指定的id对应的view。
         */
        public <V> V get(int id) {
            return (V) mHolderViews.get(id);
        }
    }
}
