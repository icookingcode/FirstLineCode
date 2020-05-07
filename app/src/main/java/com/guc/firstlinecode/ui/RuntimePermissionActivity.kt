package com.guc.firstlinecode.ui

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.guc.firstlinecode.R
import com.guc.firstlinecode.base.BaseActivity
import com.guc.firstlinecode.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_runtime_permission.*

class RuntimePermissionActivity : BaseActivity(), View.OnClickListener {
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, RuntimePermissionActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_runtime_permission)
        titleView.title = "运行时权限"
        btnMakeCall.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnMakeCall -> makeCall2()
        }
    }


    private fun makeCall2() {
        requestRuntimePermissions(arrayOf(Manifest.permission.CALL_PHONE)) { bool, _ ->
            ToastUtil.toast(context, (if (bool) "申请成功" else "已拒绝"))
            if (bool) call()
        }
    }

    private fun call() {
        try {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:10086")
            startActivity(intent)
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

}
