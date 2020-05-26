package com.guc.firstlinecode.utils

import android.app.*
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import com.guc.firstlinecode.R

/**
 * Created by guc on 2020/5/9.
 * 描述：通知工具
 */
object NotificationUtil {
    const val channelId_NORMAL = "1"
    private const val channelName_NORMAL = "基本通知"
    const val channelId_IMPORTANT = "2"
    private const val channelName_IMPORTANT = "重要通知"

    fun createChannel(context: Context) {
        //获取NotificationManager
        val manager = context.getSystemService(Service.NOTIFICATION_SERVICE) as NotificationManager
        //创建通知渠道 大于等于Android8.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel1 =
                NotificationChannel(
                    channelId_NORMAL,
                    channelName_NORMAL,
                    NotificationManager.IMPORTANCE_DEFAULT
                )
            val channel2 =
                NotificationChannel(
                    channelId_IMPORTANT,
                    channelName_IMPORTANT,
                    NotificationManager.IMPORTANCE_HIGH
                )
            manager.createNotificationChannels(listOf(channel1, channel2))
        }
    }

    fun createNotification(
        context: Context,
        channelId: String = channelId_NORMAL,
        title: String,
        text: String,
        pi: PendingIntent? = null,
        progress: Int = -1
    ): Notification = NotificationCompat.Builder(context, channelId).run {
        setContentTitle(title)
        if (text.isNotEmpty()) setContentText(text)
        setSmallIcon(R.drawable.orange)
        setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.large_orange))
        pi?.let { this.setContentIntent(it) }
        if (progress >= 0) {
            setProgress(100, progress, false)
        }
        val notification = build()
        notification
    }
}