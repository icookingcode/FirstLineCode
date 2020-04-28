package com.guc.firstlinecode

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.guc.firstlinecode.base.BaseActivity
import com.guc.firstlinecode.utils.LogG

class DialogActivity : BaseActivity() {
    val TAG = "DialogActivity"

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, DialogActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)
        LogG.loge(TAG, "onCreate")
    }

    override fun onStart() {
        super.onStart()
        LogG.loge(TAG, "onStart")
    }

    override fun onRestart() {
        super.onRestart()
        LogG.loge(TAG, "onRestart")
    }

    override fun onPause() {
        super.onPause()
        LogG.loge(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        LogG.loge(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        LogG.loge(TAG, "onDestroy")
    }
}
