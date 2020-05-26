package com.guc.firstlinecode.service

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.Environment
import android.os.IBinder
import com.guc.firstlinecode.MainActivity
import com.guc.firstlinecode.utils.LogG
import com.guc.firstlinecode.utils.NotificationUtil
import com.guc.firstlinecode.utils.ToastUtil
import com.guc.firstlinecode.utils.installApk
import okhttp3.internal.notify
import java.io.File
import java.net.HttpURLConnection

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
    private var downloadTask: DownloadTask? = null
    var downloadUrl: String? = null
    var downloadFile:File?=null
    private val downloadListener = object : DownloadListener {

        override fun onProgress(progress: Int) {
            val pi = PendingIntent.getActivity(
                this@DownloadService,
                0,
                Intent(this@DownloadService, MainActivity::class.java),
                0
            )
            getNotificationManager().notify(
                1,
                NotificationUtil.createNotification(
                    this@DownloadService,
                    NotificationUtil.channelId_NORMAL,
                    "下载中...",
                    "$progress%",
                    pi,
                    progress
                )
            )
        }

        override fun onSuccess() {
            downloadTask = null
            //下载成功后将前台服务通知关闭，并创建一个下载车成功的通知
            stopForeground(true)
            getNotificationManager().notify(
                1,
                NotificationUtil.createNotification(
                    this@DownloadService,
                    NotificationUtil.channelId_NORMAL,
                    "下载完成",
                    "",
                    progress = -1
                )
            )
            downloadFile?.apply {
                installApk(this,this@DownloadService)
            }
            ToastUtil.toast(this@DownloadService, "下载成功")
        }

        override fun onFailed() {
            downloadTask = null
            //现在失败将前台服务通知关闭，并创建一个下载失败的通知
            stopForeground(true)
            getNotificationManager().notify(
                1,
                NotificationUtil.createNotification(
                    this@DownloadService,
                    NotificationUtil.channelId_NORMAL,
                    "下载失败",
                    "",
                    progress = -1
                )
            )
            ToastUtil.toast(this@DownloadService, "下载失败")
        }

        override fun onCancel() {
            downloadTask = null
            stopForeground(true)
            ToastUtil.toast(this@DownloadService, "取消下载")
        }

        override fun onPaused() {
            downloadTask = null
            ToastUtil.toast(this@DownloadService, "已暂停")
        }

    }

    override fun onCreate() {
        super.onCreate()
        service = this
        NotificationUtil.createChannel(this)
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

    private fun getNotificationManager(): NotificationManager =
        (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)

    //用于与Activity交互
    class DownloadBinder(val service: DownloadService) : Binder() {
        fun startDownload(url: String) {
            service.downloadUrl = url
            val fileName = url.substring(url.lastIndexOf("/"))
            val dictionary =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).path
//                val dictionary =
//                    service.externalCacheDir?.path
            service.downloadFile = File(dictionary + fileName)
            if (service.downloadTask == null) {
                service.downloadTask = DownloadTask(service.downloadListener,service)
                service.downloadTask?.execute(url)
                val pi = PendingIntent.getActivity(
                    service,
                    0,
                    Intent(service, MainActivity::class.java),
                    0
                )
                service.startForeground(
                    1,
                    NotificationUtil.createNotification(
                        service,
                        NotificationUtil.channelId_NORMAL,
                        "开始下载",
                        "0%",
                        pi,
                        0
                    )
                )
                ToastUtil.toast(service, "开始下载")
            }
        }

        fun getProgress(): Int {
            LogG.loge(TAG, "getProgress  ")
            return 0
        }

        fun pauseDownload() {
            service.downloadTask?.isPaused = true
        }

        fun cancelDownload() {
            service.downloadTask?.isCanceled = true

            service.downloadUrl?.apply {
                val fileName = substring(lastIndexOf("/"))
                val dictionary =
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).path
//                val dictionary =
//                    service.externalCacheDir?.path
                val file = File(dictionary + fileName)
                if (file.exists()) {
                    file.delete()
                }
                service.getNotificationManager().cancel(1)
                service.stopForeground(true)
                ToastUtil.toast(service, "已取消下载")
            }

        }
    }
}