package com.guc.firstlinecode.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.guc.firstlinecode.R
import com.guc.firstlinecode.bean.News
import com.guc.firstlinecode.ui.fragment.NewsContentFragment
import kotlinx.android.synthetic.main.activity_news_content.*

/**
 * 新闻 内容
 */
class NewsContentActivity : AppCompatActivity() {
    companion object {
        fun start(context: Context, news: News) {
            context.startActivity(Intent(context, NewsContentActivity::class.java).apply {
                putExtra("title", news.title)
                putExtra("content", news.content)
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_content)
        supportActionBar?.hide()
        val title = intent.getStringExtra("title")
        val content = intent.getStringExtra("content")
        if (title != null && content != null) {
            titleLayout.title = title
            (newsContentFrag as NewsContentFragment).refresh(News(title, content))
        }
    }
}
