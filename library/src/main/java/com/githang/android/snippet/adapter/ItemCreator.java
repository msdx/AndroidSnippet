package com.githang.android.snippet.adapter;

import android.view.ViewGroup;

/**
 * @author msdx.android@qq.com
 * @param <T> 数据
 * @param <H> ItemHolder 子类
 */
public interface ItemCreator<T, H extends ItemHolder> {

    /**
     * 创建指定位置的 ViewHolder
     * @param position 指定位置
     * @param parent item 父布局
     * @return ViewHolder
     */
    H createHolder(int position, ViewGroup parent);

    /**
     * 设置列表里的视图内容
     *
     * @param position 在列表中的位置
     * @param holder   该位置对应的视图
     * @param data     对应位置的数据
     */
    void bindData(int position, H holder, T data);
}