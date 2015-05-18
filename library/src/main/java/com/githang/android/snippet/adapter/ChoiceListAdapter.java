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
 * @author 黄浩杭 (huanghaohang@parkingwang.com)
 * @version 0.4 15-5-13
 */
public abstract class ChoiceListAdapter<T> extends BaseAdapter {
    private Context mContext;
    private List<T> mData;
    private int mLayoutId;
    private int mChoiceId;

    /**
     * @param context
     * @param layoutId item 的布局id.
     * @param data 数据源
     * @param choiceId 选择控件的id
     */
    public ChoiceListAdapter(Context context, int layoutId, List<T> data, int choiceId) {
        mContext = context;
        mData = data;
        mLayoutId = layoutId;
        mChoiceId = choiceId;
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
     * @param view ChoiceView
     */
    protected abstract void holdView(ChoiceLayout view);

    /**
     * 绑定数据
     * @param view
     * @param position
     * @param data
     */
    protected abstract void bindData(ChoiceLayout view, int position, T data);

    /**
     * 绑定Checkable控件的Layout.
     */
    public static class ChoiceLayout extends FrameLayout implements Checkable {
        private Checkable mCheckView;
        private SparseArray<View> mHolderViews;

        protected ChoiceLayout(Context context) {
            super(context);
            mHolderViews = new SparseArray<>();
        }

        /**
         * 设置item的布局及Checkable控件的id。
         * @param layoutId 布局id
         * @param choiceId Checkable控件id
         */
        protected void setLayoutAndChoiceId(int layoutId, int choiceId) {
            View.inflate(getContext(), layoutId, this);
            mCheckView = (Checkable) findViewById(choiceId);
            ((View) mCheckView).setFocusable(false);
            ((View) mCheckView).setFocusableInTouchMode(false);
            ((View) mCheckView).setClickable(false);
        }

        /**
         * 持有子view
         * @param id
         */
        public void hold(int id) {
            mHolderViews.put(id, findViewById(id));
        }

        /**
         * 获取子view。
         * @param id
         * @param <D>
         * @return
         */
        public <D> D get(int id) {
            return (D) mHolderViews.get(id);
        }

        @Override
        public void setChecked(boolean checked) {
            mCheckView.setChecked(checked);
        }

        @Override
        public boolean isChecked() {
            return mCheckView.isChecked();
        }

        @Override
        public void toggle() {
            mCheckView.toggle();
        }
    }
}
