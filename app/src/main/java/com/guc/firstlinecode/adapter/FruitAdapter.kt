package com.guc.firstlinecode.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.guc.firstlinecode.R
import com.guc.firstlinecode.adapter.common.ViewHolder4ListView
import com.guc.firstlinecode.bean.Fruit

/**
 * Created by guc on 2020/4/29.
 * 描述：水果适配器
 */
class FruitAdapter(private val activity: Activity, private val layoutId: Int, data: List<Fruit>) :
    ArrayAdapter<Fruit>(activity, layoutId, data) {
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
        viewHolder.apply {
            setText(R.id.textView, getItem(position)?.name)
            setImageResource(R.id.imageView, getItem(position)?.imageId ?: R.drawable.apple)
        }
        return view
    }
}