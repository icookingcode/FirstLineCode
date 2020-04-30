package com.guc.firstlinecode.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.guc.firstlinecode.R
import com.guc.firstlinecode.bean.News
import kotlinx.android.synthetic.main.frag_news_content.*

/**
 * Created by guc on 2020/4/30.
 * 描述：新闻内容frag
 */
class NewsContentFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frag_news_content, container, false)
    }

    fun refresh(news: News) {
        contentLayout.visibility = View.VISIBLE
        newsTitle.text = news.title
        newsContent.text = news.content
    }
}