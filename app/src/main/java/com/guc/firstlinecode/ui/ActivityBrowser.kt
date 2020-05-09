package com.guc.firstlinecode.ui

import android.os.Bundle
import android.webkit.WebViewClient
import com.guc.firstlinecode.R
import com.guc.firstlinecode.base.BaseActivity
import kotlinx.android.synthetic.main.activity_browser.*

/**
 * webview浏览器
 */
class ActivityBrowser : BaseActivity() {
    companion object {
        const val DATA_URL = "url"
        const val DATA_TITLE = "title"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browser)
        val title = intent.getStringExtra(DATA_TITLE)
        val url = intent.getStringExtra(DATA_URL)

        titleLayout.title = title?.run {
            this
        } ?: "来自网页"
        webview.settings.javaScriptEnabled = true
        webview.webViewClient = WebViewClient()
        webview.loadUrl(url)
    }

    override fun onBackPressed() {
        if (webview.canGoBack()) {
            webview.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
