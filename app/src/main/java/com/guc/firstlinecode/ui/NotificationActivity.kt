package com.guc.firstlinecode.ui

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.core.app.NotificationCompat
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

    private lateinit var notificationManager: NotificationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        titleLayout.title = "通知"
        btnCreateChannel.setOnClickListener(this)
        btnSendNotice.setOnClickListener(this)
        btnSendStyleNotice.setOnClickListener(this)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnCreateChannel -> createChannel()
            R.id.btnSendNotice -> sendNotice()
            R.id.btnSendStyleNotice -> sendStyleNotice()
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

    private fun sendNotice() {
        val pi = PendingIntent.getActivity(
            this,
            0,
            Intent(this, ListViewActivity::class.java),
            PendingIntent.FLAG_ONE_SHOT
        )
        val notification = NotificationCompat.Builder(context, channelId2).run {
            setContentTitle("通知标题")
            setContentText("NotificationManager.IMPORTANCE_HIGH高级别的通知")
            setSmallIcon(R.drawable.orange)
            setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.large_orange))
            setAutoCancel(true)
            setContentIntent(pi)
            var notice = build()
            notice
        }
        notificationManager.notify(1, notification)
    }

    private fun sendStyleNotice() {
        val pi = PendingIntent.getActivity(
            this,
            0,
            Intent(this, ListViewActivity::class.java),
            PendingIntent.FLAG_ONE_SHOT
        )
        val notification = NotificationCompat.Builder(context, channelId).run {
            setContentTitle("可以不服输，但要会认输")
            setContentText("不服输，是挑战自我，会认输，是正确接受自我...")
            setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("不服输，是挑战自我，会认输，是正确接受自我，看到世界更广博的一面而又保持谦卑之心。阿加莎·克里斯蒂说过，“从对日常生活的观察来看，我可以说，没有谦卑的地方就没有人类”。拥有了谦卑之心的人类，对成功这件事看淡一些，才能活得更踏实和快乐。")
            )
            setSmallIcon(R.drawable.orange)
            setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.large_orange))
            setContentIntent(pi)
            setAutoCancel(true)
            var notice = build()
            notice
        }
        notificationManager.notify(2, notification)
    }
}
