package com.guc.firstlinecode.ui

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import com.guc.firstlinecode.R
import com.guc.firstlinecode.base.BaseActivity
import kotlinx.android.synthetic.main.activity_notification.*

class NotificationActivity : BaseActivity(), OnClickListener {
    companion object {
        const val channelId = "1"
        const val channelName = "基本通知"
        const val channelId2 = "2"
        const val channelName2 = "重要通知"
        fun start(context: Context) {
            context.startActivity(Intent(context, NotificationActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        titleLayout.title = "通知"
        btnCreateChannel.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnCreateChannel -> createChannel()
        }
    }

    private fun createChannel() {
        //获取NotificationManager
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        //创建通知渠道 大于等于Android8.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel1 =
                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
            val channel2 =
                NotificationChannel(channelId2, channelName2, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannels(listOf(channel1, channel2))
        }
    }
}
