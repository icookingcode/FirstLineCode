package com.guc.firstlinecode.utils.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by guc on 2020/5/20.
 * 描述：创建Retrofit接口实例
 */
object ServiceCreator {
    private const val BASE_URL = "http://192.168.44.141:8099/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(service: Class<T>): T = retrofit.create(service)
    inline fun <reified T> create(): T = create(T::class.java)
}