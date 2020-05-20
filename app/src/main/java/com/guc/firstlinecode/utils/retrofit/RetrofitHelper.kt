package com.guc.firstlinecode.utils.retrofit

import com.guc.firstlinecode.utils.LogG
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by guc on 2020/5/20.
 * 描述：RetrofitService获取
 */
object RetrofitHelper {
    private const val BASE_URL = "http://192.168.44.141:8099/"
    private val retrofit by lazy {
        val rt = Retrofit.Builder().apply {
            baseUrl(BASE_URL)
            addConverterFactory(GsonConverterFactory.create())
            LogG.loge(msg = "创建retrofit")
        }
        rt.build()
    }

    fun getAppService(): AppService = retrofit.create(AppService::class.java)

}