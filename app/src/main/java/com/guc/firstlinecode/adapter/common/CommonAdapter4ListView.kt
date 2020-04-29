package com.guc.firstlinecode.adapter.common

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

/**
 * Created by guc on 2020/4/29.
 * 描述：通用适配器
 */
abstract class CommonAdapter4ListView<T>(
    private val activity: Activity,
    private val layoutId: Int,
    data: List<T>
) : ArrayAdapter<T>(activity, layoutId, data) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder4ListView
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(layoutId, parent, false)
            viewHolder =
                ViewHolder4ListView(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder4ListView
        }
        bindData(viewHolder, position, getItem(position) as T)
        return view
    }

    //数据绑定
    abstract fun bindData(viewHolder: ViewHolder4ListView, position: Int, data: T)
}