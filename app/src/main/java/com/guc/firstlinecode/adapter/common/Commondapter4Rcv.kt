package com.guc.firstlinecode.adapter.common

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter

/**
 * Created by guc on 2020/4/29.
 * 描述：通用Rcv  可加载多种样式的Item
 */
abstract class Commondapter4Rcv<T>(val datas: List<T>) :
    Adapter<ViewHolder4RecyclerView>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder4RecyclerView {
        return ViewHolder4RecyclerView(getRootView(parent, viewType))
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder4RecyclerView, position: Int) {
        bindData(holder, position, datas[position], getItemViewType(position))
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    //获取itemView
    abstract fun getRootView(parent: ViewGroup, viewType: Int): View

    //数据绑定
    abstract fun bindData(
        viewHolder: ViewHolder4RecyclerView,
        position: Int,
        data: T,
        itemType: Int
    )
}