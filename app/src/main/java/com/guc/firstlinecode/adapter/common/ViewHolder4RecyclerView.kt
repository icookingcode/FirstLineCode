package com.guc.firstlinecode.adapter.common

import android.util.SparseArray
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by guc on 2020/4/29.
 * 描述：RecyclerView的ViewHolder
 */
class ViewHolder4RecyclerView(
    private val rootView: View,
    private val views: SparseArray<View> = SparseArray(),
    val onItemClicked: ((View?, position: Int) -> Unit)? = null
) : RecyclerView.ViewHolder(rootView), View.OnClickListener {

    var onItemClickListener: ((View?, Int) -> Unit)? = null
        set(value) {
            if (value != null)
                rootView.setOnClickListener(this)
            field = value
        }

    init {
        onItemClickListener = onItemClicked
    }

    fun <T : View> getView(resId: Int): T {
        var view = views.get(resId)
        if (view == null) {
            view = rootView.findViewById(resId)
            views.put(resId, view)
        }
        return view as T
    }

    override fun onClick(v: View?) {
        onItemClicked?.let { it(v, adapterPosition) }
        onItemClickListener?.let { it(v, adapterPosition) }
    }

    fun setText(resId: Int, content: CharSequence?): ViewHolder4RecyclerView {
        getView<TextView>(resId).text = content
        return this
    }

    fun setImageResource(resId: Int, imageResId: Int): ViewHolder4RecyclerView {
        getView<ImageView>(resId).setImageResource(imageResId)
        return this
    }
}