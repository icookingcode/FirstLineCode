package com.guc.firstlinecode.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.guc.firstlinecode.utils.ToastUtil

/**
 * Created by guc on 2020/4/30.
 * 描述：自定义广播接收器
 */
class AnotherBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(p0: Context, p1: Intent?) {
        ToastUtil.toast(p0, "Received in AnotherBroadcastReceiver")
    }
}