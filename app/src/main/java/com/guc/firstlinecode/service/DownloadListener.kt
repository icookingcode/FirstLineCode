package com.guc.firstlinecode.service

//下载回调
interface DownloadListener {
    fun onProgress(progress:Int)
    fun onSuccess()
    fun onFailed()
    fun onCancel()
    fun onPaused()
}