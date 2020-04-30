package com.guc.firstlinecode.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.guc.firstlinecode.R
import com.guc.firstlinecode.base.BaseActivity
import kotlinx.android.synthetic.main.activity_news.*

class NewsActivity : BaseActivity() {
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, NewsActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        supportActionBar?.hide()
        titleLayout.title = "新闻中心"
    }
}
