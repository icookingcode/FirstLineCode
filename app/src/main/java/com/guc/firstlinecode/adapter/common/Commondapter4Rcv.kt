package com.guc.firstlinecode.adapter.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter

/**
 * Created by guc on 2020/4/29.
 * 描述：通用Rcv
 */
abstract class Commondapter4Rcv<T>(private val layoutId: Int, val datas: List<T>) :
    Adapter<ViewHolder4RecyclerView>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder4RecyclerView {
        val root: View = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return ViewHolder4RecyclerView(root)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder4RecyclerView, position: Int) {
        bindData(holder, position, datas[position], getItemViewType(position))
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    //数据绑定
    abstract fun bindData(
        viewHolder: ViewHolder4RecyclerView,
        position: Int,
        data: T,
        itemType: Int
    )
}