package com.guc.firstlinecode

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.guc.firstlinecode.base.ActivityCollector
import com.guc.firstlinecode.base.BaseActivity
import com.guc.firstlinecode.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_first.*

class FirstActivity : BaseActivity() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, FirstActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        supportActionBar?.hide()
        button_exit.setOnClickListener {
            ActivityCollector.finishAll()
        }
        titleView.onMoreClicked = {
            val id = it?.id
            ToastUtil.toast(this, "点击了更多 viewId = $id")
        }
    }
}
