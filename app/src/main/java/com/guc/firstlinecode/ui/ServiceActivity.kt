package com.guc.firstlinecode.ui

import android.Manifest
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.View
import com.guc.firstlinecode.R
import com.guc.firstlinecode.base.BaseActivity
import com.guc.firstlinecode.service.DownloadService
import com.guc.firstlinecode.service.ForegroundService
import com.guc.firstlinecode.service.MyIntentService
import com.guc.firstlinecode.service.MyService
import com.guc.firstlinecode.utils.LogG
import com.guc.firstlinecode.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_service.*

/**
 * 服务学习
 */
class ServiceActivity : BaseActivity(), View.OnClickListener {
    val threadId = Thread.currentThread().id

    companion object {
        const val TAG = "ServiceActivity"
        fun start(context: Context) {
            context.startActivity(Intent(context, ServiceActivity::class.java))
        }
    }

    lateinit var downloadBinder: DownloadService.DownloadBinder
    var downloadService: DownloadService? = null
    private val conn = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            LogG.loge(TAG, "onServiceDisconnected")
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            LogG.loge(TAG, "onServiceConnected")
            downloadBinder = service as DownloadService.DownloadBinder
            downloadService = service.service
            downloadBinder.startDownload("https://down.qq.com/qqweb/QQ_1/android_apk/Android_8.3.6.4590_537064458.apk")
            downloadBinder.getProgress()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)
        titleLayout.title = "探究服务"
        LogG.loge(TAG, "ThreadId = $threadId")
        btnStartService.setOnClickListener(this)
        btnStopService.setOnClickListener(this)
        btnBindService.setOnClickListener(this)
        btnUnBindService.setOnClickListener(this)
        btnStartForegroundService.setOnClickListener(this)
        btnStopForegroundService.setOnClickListener(this)
        btnStartIntentService.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnStartService -> {
                startService(Intent(this, MyService::class.java))
            }
            R.id.btnStopService -> {
                stopService(Intent(this, MyService::class.java))
            }
            R.id.btnBindService -> {
                requestRuntimePermissions(
                    arrayOf(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    )
                ) {bool,_->
                    run {
                        if (bool) bindService() else ToastUtil.toast(this, "权限拒绝无法启动Service")
                    }

                }
            }
            R.id.btnUnBindService -> downloadService?.let {
                unbindService(conn)
                downloadService = null
            }
            R.id.btnStartForegroundService -> {
                startService(Intent(this, ForegroundService::class.java))
            }
            R.id.btnStopForegroundService -> {
                stopService(Intent(this, ForegroundService::class.java))
            }
            R.id.btnStartIntentService -> startService(Intent(this, MyIntentService::class.java))
        }
    }

    private fun bindService() {
        val intent = Intent(this, DownloadService::class.java)
        bindService(intent, conn, Context.BIND_AUTO_CREATE)//绑定服务
        startService(intent)
    }

}
