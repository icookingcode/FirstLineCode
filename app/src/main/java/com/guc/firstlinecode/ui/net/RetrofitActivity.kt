package com.guc.firstlinecode.ui.net

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.guc.firstlinecode.R
import com.guc.firstlinecode.adapter.common.CommonAdapter4Rcv
import com.guc.firstlinecode.adapter.common.ViewHolder4RecyclerView
import com.guc.firstlinecode.base.BaseActivity
import com.guc.firstlinecode.bean.AppInfo
import com.guc.firstlinecode.utils.retrofit.RetrofitHelper
import kotlinx.android.synthetic.main.activity_retrofit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)
        titleLayout.title = "Retrofit网络请求"

        btnGetData.setOnClickListener {
            RetrofitHelper.getAppService().getAppInfoData()
                .enqueue(object : Callback<List<AppInfo>> {
                    override fun onFailure(call: Call<List<AppInfo>>, t: Throwable) {
                        t.printStackTrace()
                    }

                    override fun onResponse(
                        call: Call<List<AppInfo>>,
                        response: Response<List<AppInfo>>
                    ) {
                        val datas = response.body()
                        if (datas != null)
                            showData(datas)
                    }

                })
        }
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
