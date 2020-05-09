package com.guc.firstlinecode.ui

import android.os.Bundle
import com.guc.firstlinecode.R
import com.guc.firstlinecode.base.BaseActivity
import com.guc.firstlinecode.utils.quickStartActivity
import kotlinx.android.synthetic.main.activity_browser.titleLayout
import kotlinx.android.synthetic.main.activity_net_technology.*

/**
 * 网络技术学习
 */
class NetTechnologyActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_net_technology)
        titleLayout.title = "使用网络技术"
        btnWebview.setOnClickListener {
            quickStartActivity<ActivityBrowser>(this) {
                putExtra(ActivityBrowser.DATA_URL, "https://blog.csdn.net/qq_31028313")
            }
        }
    }
}
