package com.guc.firstlinecode.base

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.guc.firstlinecode.receiver.MyAction
import com.guc.firstlinecode.utils.LogG

/**
 * Created by guc on 2020/4/28.
 * 描述：基类
 */
open class BaseActivity : AppCompatActivity() {
    lateinit var receiver: ForceOfflineReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityCollector.addActivity(this)
        LogG.logi("BaseActivity", "taskId : $taskId  ${javaClass.simpleName}")
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeActivity(this)
    }

    override fun onResume() {
        super.onResume()
        receiver = ForceOfflineReceiver()
        val intentFilter = IntentFilter(MyAction.FORCE_OFFLINE)
        registerReceiver(receiver, intentFilter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver)
    }

    inner class ForceOfflineReceiver : BroadcastReceiver() {
        override fun onReceive(p0: Context, p1: Intent?) {
            AlertDialog.Builder(p0).apply {
                setTitle("警告")
                setMessage("确定要退出软件？")
                setCancelable(false)
                setPositiveButton("确定") { _, _ ->
                    ActivityCollector.finishAll()
                }
                show()
            }
        }
    }
}