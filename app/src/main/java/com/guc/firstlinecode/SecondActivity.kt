package com.guc.firstlinecode

import android.net.Uri
import android.os.Bundle
import android.webkit.WebViewClient
import com.guc.firstlinecode.base.BaseActivity
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        title = "网页"
        val uri: Uri? = intent.data
        initWebView()
        webview.loadUrl(uri.toString())
    }

    private fun initWebView() {
        webview.webViewClient = WebViewClient()
        val setting = webview.settings
        setting.domStorageEnabled = true
        setting.blockNetworkImage = true
        setting.javaScriptCanOpenWindowsAutomatically = true
        setting.javaScriptEnabled = true
        setting.loadsImagesAutomatically = true
    }
}
