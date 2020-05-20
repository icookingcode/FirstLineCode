package com.guc.firstlinecode.ui.net

import android.os.Bundle
import com.guc.firstlinecode.R
import com.guc.firstlinecode.base.BaseActivity
import kotlinx.android.synthetic.main.activity_http_url_connection.*
import kotlinx.android.synthetic.main.activity_net_technology.titleLayout
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class HttpURLConnectionActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http_url_connection)
        titleLayout.title = "HttpURLConnection请求"
        btnRequest.setOnClickListener {
            sendRequestWithHttpURLConnection()
        }
        btnRequestByOkHttp.setOnClickListener {
            sendRequestWithOkhttp()
        }
    }

    private fun sendRequestWithOkhttp() {
        thread {
            try {
                val client = OkHttpClient()
//                val request = Request.Builder().url("https://www.hao123.com/").build()
                val request =
                    Request.Builder().url("http://192.168.44.141:8099/get_data.xml").build()
                val response = client.newCall(request).execute()
                val responseData = response.body?.string()
                if (responseData != null) {
                    showResponse(responseData)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun sendRequestWithHttpURLConnection() {
        thread {
            var connection: HttpURLConnection? = null
            try {
                val response = StringBuilder()
                val url = URL("https://www.baidu.com/")
                connection = url.openConnection() as HttpURLConnection
                connection.connectTimeout = 8000
                connection.readTimeout = 8000
                val input = connection.inputStream
                val reader = BufferedReader(InputStreamReader(input))
                reader.use {
                    reader.forEachLine {
                        response.append(it)
                    }
                }

                showResponse(response.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                connection?.disconnect()
            }
        }
    }

    private fun showResponse(response: String) {
        runOnUiThread {
            tvShow.text = response
        }
    }
}
