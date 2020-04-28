package com.guc.firstlinecode.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.guc.firstlinecode.utils.LogG

/**
 * Created by guc on 2020/4/28.
 * 描述：基类
 */
open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityCollector.addActivity(this)
        LogG.logi("BaseActivity", "taskId : $taskId  ${javaClass.simpleName}")
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeActivity(this)
    }
}