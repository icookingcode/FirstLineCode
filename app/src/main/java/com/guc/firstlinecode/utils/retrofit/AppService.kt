package com.guc.firstlinecode.utils.retrofit

import com.guc.firstlinecode.bean.AppInfo
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by guc on 2020/5/20.
 * 描述：retrofit接口 使用示例
 */
interface AppService {
    //http://192.168.44.141:8099/get_data.json
    @GET("get_data.json")
    fun getAppInfoData(): Call<List<AppInfo>>

    //http://192.168.44.141:8099/<page>/get_data.json
    @GET("{page}/get_data.json") //路径参数
    fun getData(@Path("page") page: Int): Call<List<AppInfo>>

    //http://192.168.44.141:8099/get_data.json?u=<user>&t=<token>
    @GET("/get_data.json") //带参数的get请求
    fun getData(@Query("u") user: String, @Query("t") token: String): Call<List<AppInfo>>

    //自动将AppInfo对象中的数据转为JSON格式的文本，并放到HTTP请求的body部分
    @POST("data/create")
    fun addData(@Body data: AppInfo): Call<ResponseBody>

    @GET("/get_data.json") //带参数的get请求
    fun getDataWithHeader(
        @Header("User-Agent") userAgent: String,
        @Header("Cache-Control") cacheControl: String
    ): Call<List<AppInfo>>
}