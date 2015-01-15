/*
 * Date: 14-6-13
 * Project: Parking Lay-by
 */
package com.githang.android.snippet.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

import java.util.ArrayList;

/**
 * 对弹出菜单的封装.
 * Author: msdx (645079761@qq.com)
 * Time: 14-6-13 下午1:51
 */
public abstract class PopMenu {
    /**
     * 上下文.
     */
    private Context mContext;
    /**
     * 菜单项
     */
    private ArrayList<Item> mItemList;
    /**
     * 列表适配器.
     */
    private ArrayAdapter<Item> mAdapter;
    /**
     * 菜单选择监听.
     */
    private OnItemSelectedListener mListener;
    /**
     * 列表.
     */
    private ListView mListView;
    /**
     * 弹出窗口.
     */
    private PopupWindow mPopupWindow;

    public PopMenu(Context context) {
        mContext = context;
        mItemList = new ArrayList<Item>(2);
        View view = onCreateView(context);
        view.setFocusableInTouchMode(true);
        mAdapter = onCreateAdapter(context, mItemList);
        mListView = findListView(view);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item item = mAdapter.getItem(position);
                if (mListener != null) {
                    mListener.selected(view, item, position);
                }
                mPopupWindow.dismiss();
            }
        });
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_MENU && mPopupWindow.isShowing()) {
                    mPopupWindow.dismiss();
                    return true;
                }
                return false;
            }
        });
        mPopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
    }

    /**
     * 菜单的界面视图.
     *
     * @param context
     * @return
     */
    protected abstract View onCreateView(Context context);

    /**
     * 菜单界面视图中的列表.
     *
     * @param view
     * @return
     */
    protected abstract ListView findListView(View view);

    /**
     * 菜单列表中的适配器.
     *
     * @param context
     * @param itemList 表示所有菜单项.
     * @return
     */
    protected abstract ArrayAdapter<Item> onCreateAdapter(Context context, ArrayList<Item> itemList);

    /**
     * 添加菜单项.
     *
     * @param text 菜单项文字内容.
     * @param id   菜单项的ID
     */
    public void addItem(String text, int id) {
        mItemList.add(new Item(text, id));
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 添加菜单项.
     *
     * @param resId 菜单项文字内容的资源ID
     * @param id    菜单项的ID.
     */
    public void addItem(int resId, int id) {
        addItem(mContext.getString(resId), id);
    }

    /**
     * 作为指定View的下拉控制显示.
     *
     * @param parent 所指定的View
     */
    public void showAsDropDown(View parent) {
        mPopupWindow.showAsDropDown(parent);
    }

    /**
     * 隐藏菜单.
     */
    public void dismiss() {
        mPopupWindow.dismiss();
    }

    /**
     * 设置菜单选择监听.
     *
     * @param listener 监听器.
     */
    public void setOnItemSelectedListener(OnItemSelectedListener listener) {
        mListener = listener;
    }

    /**
     * 当前菜单是否正在显示.
     *
     * @return
     */
    public boolean isShowing() {
        return mPopupWindow.isShowing();
    }

    /**
     * 菜单项.
     */
    public static class Item {
        public String text;
        public int id;

        public Item(String text, int id) {
            this.text = text;
            this.id = id;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    /**
     * 菜单项选择监听接口.
     */
    public static interface OnItemSelectedListener {
        /**
         * 菜单被选择时的回调接口.
         *
         * @param view     被选择的内容的View.
         * @param item     被选择的菜单项.
         * @param position 被选择的位置.
         */
        public void selected(View view, Item item, int position);
    }
}
