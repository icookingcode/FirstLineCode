package com.guc.firstlinecode.service

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.guc.firstlinecode.ui.NewsActivity
import com.guc.firstlinecode.utils.LogG
import com.guc.firstlinecode.utils.NotificationUtil

/**
 * Created by guc on 2020/5/9.
 * 描述：前台Service
 */
class ForegroundService : Service() {
    val tag = "ForegroundService"
    override fun onCreate() {
        super.onCreate()
        NotificationUtil.createChannel(this)
        val intent = Intent(this, NewsActivity::class.java)
        val pi = PendingIntent.getActivity(this, 0, intent, 0)
        val notice = NotificationUtil.createNotification(
            this,
            NotificationUtil.channelId_NORMAL,
            "通知标题",
            "通知内容",
            pi
        )
        startForeground(1, notice)
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        LogG.loge(tag, "onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        LogG.loge(tag, "onDestroy")
    }
}