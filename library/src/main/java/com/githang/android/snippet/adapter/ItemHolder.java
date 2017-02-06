package com.githang.android.snippet.adapter;

import android.support.annotation.IdRes;
import android.util.SparseArray;
import android.view.View;
import android.widget.Checkable;
import android.widget.TextView;

/**
 * @author Geek_Soledad (msdx.android@qq.com)
 * @version 2016-09-02
 * @since 2016-09-02
 */

public interface ItemHolder {
    abstract class AbstractItemHolder implements ItemHolder {
        public View itemView;

        public AbstractItemHolder(View itemView) {
            this.itemView = itemView;
            itemView.setTag(this);
        }
    }

    class DefaultHolder extends AbstractItemHolder {
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
}
