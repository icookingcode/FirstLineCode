package com.guc.firstlinecode.ui.jetpack.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.guc.firstlinecode.utils.LogG

/**
 * Created by guc on 2020/6/2.
 * 描述：实现LifecycleObserver,监听Activity声明周期
 */
class MyObserver : LifecycleObserver {
    companion object {
        const val TAG = "MyObserver"
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun activityStart() {
        LogG.loge(TAG, "activityStart")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun activityStop() {
        LogG.loge(TAG, "activityStop")
    }
}