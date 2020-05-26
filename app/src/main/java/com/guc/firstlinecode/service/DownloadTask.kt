package com.guc.firstlinecode.service

import android.content.Context
import android.os.AsyncTask
import android.os.Environment
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.InputStream
import java.io.RandomAccessFile

/**
 * Created by guc on 2020/5/9.
 * 描述：通过AsyncTask实现下载功能
 *  AsyncTask<Params, Progress, Result>
 *      Params:参数
 *      Progress:进度
 *      Result:执行结果
 */
class DownloadTask(val downloadListener: DownloadListener,val context:Context) : AsyncTask<String, Int, Int>() {

    var isCanceled = false
    var isPaused = false
    var lastProgress = 0

    companion object {
        const val TYPE_SUCCESS = 0
        const val TYPE_FAILED = 1
        const val TYPE_PAUSED = 2
        const val TYPE_CANCEL = 3
    }

    //后台任务开始执行前调用
    override fun onPreExecute() {
        super.onPreExecute()
    }

    //子线程运行
    override fun doInBackground(vararg params: String): Int {
        var inputStream: InputStream? = null
        var savedFile: RandomAccessFile? = null
        var file: File? = null

        try {
            var downloadedLength = 0L
            val url = params[0]
            val fileName = url.substring(url.lastIndexOf("/"))
            val dictionary =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).path
//            val dictionary =
//                context.externalCacheDir?.path
            file = File(dictionary + fileName)

            if (file.exists()) {
                downloadedLength = file.length()
            }else{
                file.parentFile.mkdirs()
                file.createNewFile()
            }
            val contentLength = getContentLength(url)
            if (contentLength == 0L) {
                return TYPE_FAILED
            } else if (contentLength == downloadedLength) {
                return TYPE_SUCCESS
            }
            val client = OkHttpClient()
            val request = Request.Builder()
                //断点下载
                .addHeader("RANGE", "bytes=$downloadedLength-$contentLength")
                .url(url)
                .build()
            val response = client.newCall(request).execute()
            inputStream = response.body?.byteStream()
            if (inputStream == null) {
                return TYPE_FAILED
            }
            savedFile = RandomAccessFile(file, "rw")
            savedFile.seek(downloadedLength)
            val b = ByteArray(1024)
            var total = 0
            inputStream.use {
                var len = it.read(b)
                while ((len) != -1) {
                    if (isCanceled) return TYPE_CANCEL
                    else if (isPaused) return TYPE_PAUSED
                    else {
                        total += len
                        savedFile.write(b, 0, len)

                        val progress = ((total + downloadedLength) * 100 / contentLength).toInt()
                        publishProgress(progress)
                        len = it.read(b)
                    }

                }
                return TYPE_SUCCESS
            }

        } catch (e: Exception) {
            e.printStackTrace()
            inputStream?.close()
            savedFile?.close()
            if (isCanceled) {
                file?.delete()
            }
        }
        return TYPE_FAILED
    }

    //运行在主线程，可对UI进行更新进度
    override fun onProgressUpdate(vararg values: Int?) {
        val progress = values[0] ?: return
        if (progress > lastProgress) {
            downloadListener.onProgress(progress)
            lastProgress = progress
        }
    }

    //后台任务执行完毕后调用
    override fun onPostExecute(result: Int?) {
        when (result) {
            TYPE_SUCCESS -> downloadListener.onSuccess()
            TYPE_PAUSED -> downloadListener.onPaused()
            TYPE_CANCEL -> downloadListener.onCancel()
            TYPE_FAILED -> downloadListener.onFailed()
            else -> {
            }
        }
    }

    private fun getContentLength(url: String): Long {
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()
        val response = client.newCall(request).execute()
        if (response.isSuccessful) {
            val contentLength = response.body?.contentLength() ?: 0
            response.body?.close()
            return contentLength

        }
        return 0L
    }
}