package com.guc.firstlinecode.ui.jetpack.livedata

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.guc.firstlinecode.R
import com.guc.firstlinecode.base.BaseActivity
import com.guc.firstlinecode.bean.News
import kotlinx.android.synthetic.main.activity_live_data.*

/**
 * 响应式编程组件，数据变化时通知给观察者
 */
class LiveDataActivity : BaseActivity() {
    lateinit var viewModel: DataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)
        titleLayout.title = "LiveData"
        viewModel = ViewModelProvider(
            this.viewModelStore,
            DataViewModelFactory(2)
        ).get(DataViewModel::class.java)
        btnPlusOne.setOnClickListener {
            viewModel.plusOne()
        }
        btnClear.setOnClickListener {
            viewModel.clear()
        }

        viewModel.counter.observe(this, Observer {
            infoText.text = it.toString()
        })

        btnGetNews.setOnClickListener {
            viewModel.setNews(News("标题", "内容"))
        }
//        viewModel.newsLiveData.observe(this, Observer { news -> infoText.text = "${news.title}${news.content}"})
        viewModel.news.observe(this, Observer { infoText.text = it })

    }
}