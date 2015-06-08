package com.githang.android.snippet.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Checkable;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

/**
 * 可选择的ListAdapter.
 *
 * @author 黄浩杭 (huanghaohang@parkingwang.com)
 * @version 0.4 15-5-13
 */
public abstract class ChoiceListAdapter<T> extends BaseAdapter {
    private Context mContext;
    private List<T> mData;
    private int mLayoutId;
    private int[] mChoiceId;

    /**
     * @param context
     * @param layoutId item 的布局id.
     * @param data     数据源
     * @param choiceId 选择控件的id
     */
    public ChoiceListAdapter(Context context, int layoutId, List<T> data, int... choiceId) {
        mContext = context;
        mData = data;
        mLayoutId = layoutId;
        mChoiceId = choiceId;
    }


    /**
     * @param context
     * @param layoutId The layout id if the item view.
     * @param data     data source
     * @param choiceId checkable view id
     */
    public ChoiceListAdapter(Context context, int layoutId, T[] data, int... choiceId) {
        this(context, layoutId, Arrays.asList(data), choiceId);
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ChoiceLayout view;
        if (convertView == null) {
            view = new ChoiceLayout(mContext);
            view.setLayoutAndChoiceId(mLayoutId, mChoiceId);
            holdView(view);
        } else {
            view = (ChoiceLayout) convertView;
        }
        bindData(view, position, mData.get(position));
        return view;
    }

    /**
     * 持有item的子view。在该方法内调用ChoiceView的hold方法，把子view添加进去。
     *
     * @param view ChoiceView
     */
    protected abstract void holdView(ChoiceLayout view);

    /**
     * 绑定数据
     *
     * @param view
     * @param position
     * @param data
     */
    protected abstract void bindData(ChoiceLayout view, int position, T data);

    /**
     * 绑定Checkable控件的Layout.
     */
    public static class ChoiceLayout extends FrameLayout implements Checkable {
        private Checkable[] mCheckViews;
        private SparseArray<View> mHolderViews;
        private boolean mChecked;
        /**
         * 是否强制设为选中状态
         */
        private boolean mForceChecked;

        protected ChoiceLayout(Context context) {
            super(context);
            mHolderViews = new SparseArray<>();
        }

        /**
         * 设置item的布局及Checkable控件的id。
         *
         * @param layoutId 布局id
         * @param choiceId Checkable控件id
         */
        protected void setLayoutAndChoiceId(int layoutId, int... choiceId) {
            View.inflate(getContext(), layoutId, this);
            mCheckViews = new Checkable[choiceId.length];
            for (int i = 0; i < choiceId.length; i++) {
                View checkedView = findViewById(choiceId[i]);
                checkedView.setFocusable(false);
                checkedView.setFocusableInTouchMode(false);
                checkedView.setClickable(false);
                mHolderViews.put(choiceId[i], checkedView);
                mCheckViews[i] = (Checkable) checkedView;
            }
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

        public void setText(int id, int stringId) {
            TextView textView = get(id);
            textView.setText(stringId);
        }

        public void setText(int id, String text) {
            TextView textView = get(id);
            textView.setText(text);
        }

        public void setChecked(int id, boolean checked) {
            Checkable checkable = get(id);
            checkable.setChecked(checked);
        }

        @Override
        public void setChecked(boolean checked) {
            checked |= mForceChecked;
            for (Checkable checkable : mCheckViews) {
                checkable.setChecked(checked);
            }
            mChecked = checked;
        }

        @Override
        public boolean isChecked() {
            return mChecked;
        }

        /**
         * 是否为强制选中状态。
         *
         * @return 如果是强制选中状态返回true，否则返回false。
         */
        public boolean isForceChecked() {
            return mForceChecked;
        }

        /**
         * 设置是否强制选中状态。
         *
         * @param forceChecked 是否强制选中状态。
         */
        public void setForceChecked(boolean forceChecked) {
            mForceChecked = forceChecked;
        }

        @Override
        public void toggle() {
            setChecked(mForceChecked | !mChecked);
        }
    }
}
