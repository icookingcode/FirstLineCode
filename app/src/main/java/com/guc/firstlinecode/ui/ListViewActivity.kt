package com.guc.firstlinecode.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.guc.firstlinecode.R
import com.guc.firstlinecode.adapter.FruitAdapter
import com.guc.firstlinecode.base.BaseActivity
import com.guc.firstlinecode.bean.Fruit
import com.guc.firstlinecode.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_list_view.*

/**
 * ListView
 * ArrayAdapter
 */
class ListViewActivity : BaseActivity() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, ListViewActivity::class.java))
        }
    }

    private val datas = ArrayList<Fruit>();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)
        supportActionBar?.hide()
        titleLayout.title = "ListView"
        val adapter = FruitAdapter(this, R.layout.item_fruit, datas)
        listView.adapter = adapter
        loadFruits()
        listView.setOnItemClickListener { _, _, pos, _ ->
            val fruit = datas[pos]
            ToastUtil.toast(this, "您点击了${fruit.name}")
        }
    }

    private fun loadFruits() {
        repeat(3) {
            datas.add(Fruit("苹果", R.drawable.apple))
            datas.add(Fruit("香蕉", R.drawable.banana))
            datas.add(Fruit("橘子", R.drawable.orange))
        }
    }
}
