package com.guc.firstlinecode.utils.retrofit

import com.guc.firstlinecode.bean.AppInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * Created by guc on 2020/5/21.
 * 描述：Api接口
 */
object Api {
    private val service = ServiceCreator.create<AppService>()
    suspend fun getAppInfoData(): List<AppInfo> {
        return service.getAppInfoData().await()
    }
}

suspend fun <T> Call<T>.await(): T {
    return suspendCoroutine { continuation ->
        enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {
                continuation.resumeWithException(t)
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                val body = response.body()
                if (body != null) continuation.resume(body)
                else continuation.resumeWithException(RuntimeException("response body is null"))
            }

        })
    }
}