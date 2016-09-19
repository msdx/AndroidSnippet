package com.githang.android.snippet.adapter;

import android.view.ViewGroup;

public interface ItemCreator<T, H extends ItemHolder> {

    H createHolder(int position, ViewGroup parent);

    /**
     * 设置列表里的视图内容
     *
     * @param position 在列表中的位置
     * @param holder   该位置对应的视图
     */
    void bindData(int position, H holder, T data);
}