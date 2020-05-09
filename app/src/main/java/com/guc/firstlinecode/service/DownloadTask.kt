package com.guc.firstlinecode.service

import android.os.AsyncTask

/**
 * Created by guc on 2020/5/9.
 * 描述：通过AsyncTask实现下载功能
 *  AsyncTask<Params, Progress, Result>
 *      Params:参数
 *      Progress:进度
 *      Result:执行结果
 */
class DownloadTask : AsyncTask<Unit, Int, Boolean>() {
    //后台任务开始执行前调用
    override fun onPreExecute() {
        super.onPreExecute()
    }

    //子线程运行
    override fun doInBackground(vararg params: Unit?): Boolean {
        TODO("Not yet implemented")
    }

    //运行在主线程，可对UI进行更新进度
    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
    }

    //后台任务执行完毕后调用
    override fun onPostExecute(result: Boolean?) {
        super.onPostExecute(result)
    }
}