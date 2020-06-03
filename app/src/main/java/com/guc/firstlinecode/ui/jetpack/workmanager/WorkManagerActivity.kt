package com.guc.firstlinecode.ui.jetpack.workmanager

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.work.*
import com.guc.firstlinecode.R
import com.guc.firstlinecode.base.BaseActivity
import com.guc.firstlinecode.utils.LogG
import kotlinx.android.synthetic.main.activity_room.titleLayout
import kotlinx.android.synthetic.main.activity_work_manager.*
import java.util.concurrent.TimeUnit

/**
 * WorkManager:适合处理一些要求定时执行的任务
 */
class WorkManagerActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager)
        titleLayout.title = "WorkManager"
        val requestOneTime = OneTimeWorkRequest.Builder(MyWorker::class.java)
            .addTag(MyWorker.TAG)
            .setInitialDelay(30, TimeUnit.SECONDS)
            .setBackoffCriteria(BackoffPolicy.LINEAR, 10, TimeUnit.SECONDS)
            .build()
        val request = PeriodicWorkRequest.Builder(MyWorker::class.java, 15, TimeUnit.SECONDS)
            .addTag(MyWorker.TAG).build()
        btnDoWork.setOnClickListener {
            WorkManager.getInstance(this).enqueue(request)
            WorkManager.getInstance(this).enqueue(requestOneTime)
        }
        btnCancelWork.setOnClickListener {
            WorkManager.getInstance(this).cancelAllWorkByTag(MyWorker.TAG)
        }
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(requestOneTime.id).observe(this,
            Observer {
                if (it.state == WorkInfo.State.SUCCEEDED) {
                    LogG.loge(msg = "do work succeed")
                } else if (it.state == WorkInfo.State.FAILED) {
                    LogG.loge(msg = "do work failed")
                }
            })
    }
}