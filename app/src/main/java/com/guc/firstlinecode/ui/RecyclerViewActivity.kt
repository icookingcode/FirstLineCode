package com.guc.firstlinecode.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.guc.firstlinecode.R
import com.guc.firstlinecode.adapter.FruitAdapter4Rcv
import com.guc.firstlinecode.base.BaseActivity
import com.guc.firstlinecode.bean.Fruit
import com.guc.firstlinecode.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_list_view.titleLayout
import kotlinx.android.synthetic.main.activity_recyler_view.*

class RecyclerViewActivity : BaseActivity() {
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, RecyclerViewActivity::class.java))
        }
    }

    private val datas = ArrayList<Fruit>();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyler_view)
        supportActionBar?.hide()
        titleLayout.title = "RecyclerView"
        initHorizontalRecyclerView()
        initStaggeredRecyclerView()
        loadFruits()
    }

    private fun initHorizontalRecyclerView() {
        val adapter = FruitAdapter4Rcv(R.layout.item_fruit, datas)
        val lm = LinearLayoutManager(this)
        lm.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = lm
        recyclerView.adapter = adapter
        adapter.onItemClicked = { _, i -> ToastUtil.toast(this, "点击了$i") }
    }

    private fun initStaggeredRecyclerView() {
        val adapter = FruitAdapter4Rcv(R.layout.item_fruit, datas)
        val lm = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        recyclerViewStaggered.layoutManager = lm
        recyclerViewStaggered.adapter = adapter
        adapter.onItemClicked = { _, i -> ToastUtil.toast(this, "点击了$i") }
    }

    private fun loadFruits() {
        repeat(3) {
            datas.add(Fruit(getRandomLengthString("苹果"), R.drawable.apple))
            datas.add(Fruit(getRandomLengthString("香蕉"), R.drawable.banana))
            datas.add(Fruit(getRandomLengthString("橘子"), R.drawable.orange))
        }
    }

    private fun getRandomLengthString(string: String): String {
        val n = (1..20).random()
        val builder = StringBuilder()
        repeat(n) {
            builder.append(string)
        }
        return builder.toString()
    }
}
