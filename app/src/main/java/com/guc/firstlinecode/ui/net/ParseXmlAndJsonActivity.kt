package com.guc.firstlinecode.ui.net

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.guc.firstlinecode.R
import com.guc.firstlinecode.adapter.common.CommonAdapter4Rcv
import com.guc.firstlinecode.adapter.common.ViewHolder4RecyclerView
import com.guc.firstlinecode.base.BaseActivity
import com.guc.firstlinecode.bean.AppInfo
import kotlinx.android.synthetic.main.activity_notification.titleLayout
import kotlinx.android.synthetic.main.activity_parse_xml.*
import okhttp3.OkHttpClient
import okhttp3.Request
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.StringReader
import kotlin.concurrent.thread

class ParseXmlAndJsonActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parse_xml)
        titleLayout.title = "XML解析与json解析"
        btnParseXml.setOnClickListener {
            getXmlData()
        }
        btnParseJson.setOnClickListener {
            getJsonData()
        }
    }

    private fun getJsonData() {
        thread {
            try {
                val client = OkHttpClient()
                val request =
                    Request.Builder().url("http://192.168.44.141:8099/get_data.json").build()
                val response = client.newCall(request).execute()
                val responseData = response.body?.string()
                if (responseData != null) {
                    parseJson(responseData)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    //解析json数据
    private fun parseJson(responseData: String) {
        val gson = Gson()
        val type = object : TypeToken<List<AppInfo>>() {}.type
        val datas = gson.fromJson<List<AppInfo>>(responseData, type)
        showData(datas)
    }

    private fun getXmlData() {
        thread {
            try {
                val client = OkHttpClient()
                val request =
                    Request.Builder().url("http://192.168.44.141:8099/get_data.xml").build()
                val response = client.newCall(request).execute()
                val responseData = response.body?.string()
                if (responseData != null) {
                    parseXmlWithPull(responseData)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    //xml解析
    private fun parseXmlWithPull(responseData: String) {
        val datas = ArrayList<AppInfo>()
        try {
            var data: AppInfo
            val factory = XmlPullParserFactory.newInstance()
            val xmlPullParser = factory.newPullParser()
            xmlPullParser.setInput(StringReader(responseData))
            var eventType = xmlPullParser.eventType
            var id = ""
            var name = ""
            var version = ""
            while (eventType != XmlPullParser.END_DOCUMENT) {
                val nodeName = xmlPullParser.name
                when (eventType) {
                    //开始解析某个节点
                    XmlPullParser.START_TAG -> {
                        when (nodeName) {
                            "id" -> id = xmlPullParser.nextText()
                            "name" -> name = xmlPullParser.nextText()
                            "version" -> version = xmlPullParser.nextText()
                        }
                    }
                    XmlPullParser.END_TAG -> {
                        if ("app" == nodeName) {
                            data = AppInfo(id, name, version)
                            datas.add(data)
                        }
                    }
                }
                eventType = xmlPullParser.next()
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
        showData(datas)
    }

    private fun showData(datas: List<AppInfo>) {
        runOnUiThread {
            val adapter = object : CommonAdapter4Rcv<AppInfo>(datas) {
                override fun getRootView(parent: ViewGroup, viewType: Int): View {
                    return LayoutInflater.from(parent.context)
                        .inflate(android.R.layout.simple_list_item_1, parent, false)
                }

                override fun bindData(
                    viewHolder: ViewHolder4RecyclerView,
                    position: Int,
                    data: AppInfo,
                    itemType: Int
                ) {
                    viewHolder.apply {
                        setText(
                            android.R.id.text1, "${data.id} \n${data.name}\n" +
                                    "${data.version}"
                        )
                    }
                }
            }
            rcvContent.layoutManager = LinearLayoutManager(this)
            rcvContent.adapter = adapter
        }
    }
}
