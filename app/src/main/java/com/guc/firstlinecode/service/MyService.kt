package com.guc.firstlinecode.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.guc.firstlinecode.utils.LogG

/**
 * Created by guc on 2020/5/9.
 * 描述：不绑定的Service
 */
class MyService : Service() {
    val tag = "MyService"
    val threadId = Thread.currentThread().id
    override fun onCreate() {
        super.onCreate()
        LogG.loge(tag, "onCreate threadId = $threadId")
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        LogG.loge(tag, "onStartCommand  threadId = $threadId")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        LogG.loge(tag, "onDestroy threadId = $threadId")
    }
}