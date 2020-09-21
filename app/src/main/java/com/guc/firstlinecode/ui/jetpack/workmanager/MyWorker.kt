package com.guc.firstlinecode.ui.jetpack.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.guc.firstlinecode.utils.LogG

/**
 * Created by guc on 2020/6/3.
 * 描述：Worker
 */
class MyWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    companion object {
        const val TAG = "MyWorker"
    }

    private var count = 0
    override fun doWork(): Result {
        count++
        LogG.loge(TAG, "${TAG}:执行${count}次")
        return Result.success() //执行成功
//        return Result.retry() //执行失败，可重试
//        return Result.failure()//执行失败
    }
}