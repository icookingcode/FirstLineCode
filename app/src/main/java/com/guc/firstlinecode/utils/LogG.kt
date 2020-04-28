package com.guc.firstlinecode.utils

import android.util.Log

/**
 * Created by guc on 2020/4/28.
 * 描述：自定义log类
 */
object LogG {
    var logOpen = true

    /**
     * Debug
     */
    fun logd(tag: String = "GUC", msg: String) {
        if (logOpen) Log.d(tag, msg)
    }

    /**
     * Error
     */
    fun loge(tag: String = "GUC", msg: String) {
        if (logOpen) Log.e(tag, msg)
    }

    /**
     * Info
     */
    fun logi(tag: String = "GUC", msg: String) {
        if (logOpen) Log.i(tag, msg)
    }

    /**
     * Verbose
     */
    fun logv(tag: String = "GUC", msg: String) {
        if (logOpen) Log.v(tag, msg)
    }

    /**
     * Warn
     */
    fun logw(tag: String = "GUC", msg: String) {
        if (logOpen) Log.w(tag, msg)
    }
}
