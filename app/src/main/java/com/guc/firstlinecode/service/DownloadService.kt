package com.guc.firstlinecode.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.guc.firstlinecode.utils.LogG

/**
 * Created by guc on 2020/5/9.
 * 描述：下载Service
 */
class DownloadService : Service() {
    companion object {
        const val TAG = "DownloadService"
    }

    lateinit var service: DownloadService
    val threadId = Thread.currentThread().id
    override fun onCreate() {
        super.onCreate()
        service = this
        LogG.loge(TAG, "onCreate threadId = $threadId")
    }

    override fun onBind(intent: Intent?): IBinder? = DownloadBinder(this)

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        LogG.loge(TAG, "onStartCommand  threadId = $threadId")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        LogG.loge(TAG, "onDestroy threadId = $threadId")
    }

    //用于与Activity交互
    class DownloadBinder(val service: DownloadService) : Binder() {
        fun startDownload() {
            LogG.loge(TAG, "startDownload  ")
        }

        fun getProgress(): Int {
            LogG.loge(TAG, "getProgress  ")
            return 0
        }
    }
}