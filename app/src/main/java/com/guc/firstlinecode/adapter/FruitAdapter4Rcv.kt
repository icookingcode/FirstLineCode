package com.guc.firstlinecode.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.guc.firstlinecode.R
import com.guc.firstlinecode.adapter.common.ViewHolder4RecyclerView
import com.guc.firstlinecode.bean.Fruit

/**
 * Created by guc on 2020/4/29.
 * 描述：Rcv
 */
class FruitAdapter4Rcv(private val layoutId: Int, val datas: List<Fruit>) :
    Adapter<ViewHolder4RecyclerView>() {
    var onItemClicked: ((View?, Int) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder4RecyclerView {
        val root: View = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        if (onItemClicked == null) {
            return ViewHolder4RecyclerView(root)
        } else {
            return ViewHolder4RecyclerView(
                root,
                onItemClicked = { view, position -> onItemClicked?.let { it(view, position) } })
        }
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder4RecyclerView, position: Int) {
        holder.apply {
            setText(R.id.textView, datas[position].name)
            setImageResource(R.id.imageView, datas[position].imageId)
        }
    }
}