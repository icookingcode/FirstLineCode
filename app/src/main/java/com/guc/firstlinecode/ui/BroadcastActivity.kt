package com.guc.firstlinecode.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import com.guc.firstlinecode.R
import com.guc.firstlinecode.base.BaseActivity
import com.guc.firstlinecode.receiver.MyAction
import kotlinx.android.synthetic.main.activity_broadcast.*
import java.util.*

class BroadcastActivity : BaseActivity(), View.OnClickListener {
    private lateinit var timeChangeReceiver: TimeChangeReceiver

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, BroadcastActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast)
        supportActionBar?.hide()
        titleLayout.title = "广播"
        btnDynamicReceiver.setOnClickListener(this)
        btnSendBroadcast.setOnClickListener(this)
        btnSendOrderBroadcast.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnDynamicReceiver -> createDynamicReceiver()
            R.id.btnSendBroadcast -> send()
            R.id.btnSendOrderBroadcast -> send(true)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(timeChangeReceiver)
    }

    //动态注册监听  接收系统广播
    private fun createDynamicReceiver() {
        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_TIME_TICK)  //系统每个一分钟发一次
        timeChangeReceiver = TimeChangeReceiver()
        registerReceiver(timeChangeReceiver, intentFilter)
    }

    private fun send(isOrder: Boolean = false) {
        val intent = Intent()
        intent.action = MyAction.MY_BROADCAST
        intent.setPackage(packageName)
        if (isOrder) sendOrderedBroadcast(intent, null) else sendBroadcast(intent)
    }

    /**
     * 系统时间变化广播接收
     */
    inner class TimeChangeReceiver : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            tvShow.text = Date().toString()
        }
    }
}
