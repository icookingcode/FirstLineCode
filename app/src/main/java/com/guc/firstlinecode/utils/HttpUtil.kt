package com.guc.firstlinecode.utils

import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request


/**
 * Created by guc on 2020/5/20.
 * 描述：网络请求工具
 */
object HttpUtil {

    /**
     * GET 请求
     */
    fun sendGetRequest(url: String, callback: Callback) {
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()
        client.newCall(request).enqueue(callback)
    }

    /**
     * POST 请求
     */
    fun setPostRequest(url: String, vararg params: Pair<String, String>, callback: Callback) {
        val client = OkHttpClient()
        val reqBody = FormBody.Builder().apply {
            for (pair in params) {
                add(pair.first, pair.second)
            }
        }.build()
        val request = Request.Builder().url(url).post(reqBody).build()
        client.newCall(request).enqueue(callback)
    }
}