package com.guc.firstlinecode.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.guc.firstlinecode.R
import com.guc.firstlinecode.adapter.common.CommonAdapter4Rcv
import com.guc.firstlinecode.adapter.common.ViewHolder4RecyclerView
import com.guc.firstlinecode.bean.News
import com.guc.firstlinecode.ui.NewsContentActivity
import com.guc.firstlinecode.utils.times
import kotlinx.android.synthetic.main.activity_news.*
import kotlinx.android.synthetic.main.frag_news_title.*

/**
 * Created by guc on 2020/4/30.
 * 描述：新闻标题frag
 */
class NewsTitleFragment : Fragment() {
    private var isTwoPane = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frag_news_title, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isTwoPane = activity?.findViewById<View>(R.id.newsContentLayout) != null
        newsTitleRcv.layoutManager = LinearLayoutManager(activity)
        val adapter = Adapter(getNews())
        newsTitleRcv.adapter = adapter

    }

    private fun getNews(): List<News> {
        val newsList = ArrayList<News>()
        for (i in 1..50) {
            newsList.add(News("标题$i", getRandomLengthString("这是标题$i 的新闻内容")))
        }
        return newsList
    }

    private fun getRandomLengthString(string: String): String = string * (1..20).random()


    inner class Adapter(datas: List<News>) : CommonAdapter4Rcv<News>(datas) {
        override fun getRootView(parent: ViewGroup, viewType: Int): View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)

        override fun bindData(
            viewHolder: ViewHolder4RecyclerView,
            position: Int,
            data: News,
            itemType: Int
        ) {
            viewHolder.apply {
                setText(R.id.newsTitle, data.title)
                onItemClickListener = { _, _ ->
                    if (isTwoPane) {
                        val frag = newsContentFrag as NewsContentFragment
                        frag.refresh(data)
                    } else {
                        NewsContentActivity.start(context, data)
                    }
                }
            }
        }
    }

}