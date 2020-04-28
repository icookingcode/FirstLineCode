package com.guc.firstlinecode.base

import android.app.Activity
import android.os.Process

/**
 * Created by guc on 2020/4/28.
 * 描述：管理Activity
 */
object ActivityCollector {
    private val activities = ArrayList<Activity>()

    fun addActivity(activity: Activity) {
        activities.add(activity)
    }

    fun removeActivity(activity: Activity) {
        activities.remove(activity)
    }

    fun finishAll() {
        for (a in activities) {
            if (!a.isFinishing) a.finish()
        }
        activities.clear()
        Process.killProcess(Process.myPid())
    }

}