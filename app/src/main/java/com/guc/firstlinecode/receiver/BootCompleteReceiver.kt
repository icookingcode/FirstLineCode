package com.guc.firstlinecode.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.guc.firstlinecode.utils.ToastUtil

/**
 * Created by guc on 2020/4/30.
 * 描述：开机广播接收
 */
class BootCompleteReceiver : BroadcastReceiver() {
    override fun onReceive(p0: Context, p1: Intent?) {
        ToastUtil.toast(p0, "开机了", Toast.LENGTH_LONG)
    }
}