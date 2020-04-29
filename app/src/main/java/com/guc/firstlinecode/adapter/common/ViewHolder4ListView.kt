package com.guc.firstlinecode.adapter.common

import android.util.SparseArray
import android.view.View
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by guc on 2020/4/29.
 * 描述：通用ListView的ViewHolder
 */
class ViewHolder4ListView(
    private val parent: View,
    private val views: SparseArray<View> = SparseArray()
) {

    fun <T : View> getView(resId: Int): T {
        var view = views.get(resId)
        if (view == null) {
            view = parent.findViewById(resId)
            views.put(resId, view)
        }
        return view as T
    }

    fun setText(resId: Int, content: CharSequence?): ViewHolder4ListView {
        getView<TextView>(resId).text = content
        return this
    }

    fun setImageResource(resId: Int, imageResId: Int): ViewHolder4ListView {
        getView<ImageView>(resId).setImageResource(imageResId)
        return this
    }
}