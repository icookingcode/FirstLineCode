package com.guc.firstlinecode.ui.jetpack

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.guc.firstlinecode.R
import com.guc.firstlinecode.adapter.common.CommonAdapter4Rcv
import com.guc.firstlinecode.adapter.common.ViewHolder4RecyclerView
import com.guc.firstlinecode.base.BaseActivity
import com.guc.firstlinecode.bean.BookEntity
import com.guc.firstlinecode.db.BookDatabase
import kotlinx.android.synthetic.main.activity_room.*
import kotlin.concurrent.thread

/**
 * Room:ROM框架使用
 */
class RoomActivity : BaseActivity() {
    lateinit var adapter: CommonAdapter4Rcv<BookEntity>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        val bookDao = BookDatabase.getDatabase(this).bookDao()
        titleLayout.title = "Room 框架"
        rcvContent.layoutManager = LinearLayoutManager(this)
        btnGetBooks.setOnClickListener {
            thread {
                val datas = bookDao.loadAllBooks()
                adapter = object : CommonAdapter4Rcv<BookEntity>(datas) {
                    override fun getRootView(parent: ViewGroup, viewType: Int): View {
                        return layoutInflater.inflate(R.layout.item_news, parent, false)
                    }

                    override fun bindData(
                        viewHolder: ViewHolder4RecyclerView,
                        position: Int,
                        data: BookEntity,
                        itemType: Int
                    ) {
                        viewHolder.apply {
                            setText(R.id.newsTitle, "${data.name}   作者：${data.author}")
                        }
                    }
                }
                runOnUiThread {
                    rcvContent.adapter = adapter
                }
            }
        }
    }
}