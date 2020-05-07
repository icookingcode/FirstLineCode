package com.guc.firstlinecode.base

import android.content.*
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.guc.firstlinecode.receiver.MyAction
import com.guc.firstlinecode.utils.LogG
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by guc on 2020/4/28.
 * 描述：基类
 */
open class BaseActivity : AppCompatActivity() {
    companion object {
        const val REQUEST_CODE: Int = 1
        const val REQUEST_CODE_SETTING = 2
        private const val DEF_DENIED_MESSAGE = "您拒绝权限申请，此功能将不能正常使用，您可以去设置页面重新授权"
        private const val DEF_DENIED_CLOSE_BTN_TEXT = "关闭"
        private const val DEF_RATIONAL_MESSAGE = "此功能需要您授权，否则将不能正常使用"
        private const val DEF_RATIONAL_BTN_TEXT = "去设置"
    }

    protected lateinit var context: Context
    private lateinit var permissionListener: (Boolean, Array<String>?) -> Unit
    private lateinit var permissions: Array<String>
    lateinit var receiver: ForceOfflineReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        supportActionBar?.hide()
        ActivityCollector.addActivity(this)
        LogG.logi("BaseActivity", "taskId : $taskId  ${javaClass.simpleName}")
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeActivity(this)
    }

    override fun onResume() {
        super.onResume()
        receiver = ForceOfflineReceiver()
        val intentFilter = IntentFilter(MyAction.FORCE_OFFLINE)
        registerReceiver(receiver, intentFilter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_CODE_SETTING -> requestRuntimePermissions(permissions, permissionListener)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE -> {
                val grantedPermissions = LinkedList<String>()
                val deniedPermissions = LinkedList<String>()
                for ((index, permission) in permissions.withIndex()) {
                    if (grantResults[index] == PackageManager.PERMISSION_GRANTED) grantedPermissions.add(
                        permission
                    ) else deniedPermissions.add(permission)
                }
                if (grantedPermissions.isNotEmpty() && deniedPermissions.isEmpty()) {
                    permissionListener(true, null)
                } else if (deniedPermissions.isNotEmpty()) {
                    val permissions = Array<String>(deniedPermissions.size) { _ -> "" }
                    deniedPermissions.toArray(permissions)
                    showDeniedDialog(permissions);
                }
            }
        }
    }

    /**
     * 请求权限
     */
    fun requestRuntimePermissions(
        permissions: Array<String>,
        permissionListener: (Boolean, Array<String>?) -> Unit
    ) {
        this.permissionListener = permissionListener
        this.permissions = permissions
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            this.permissionListener(true, null)
            return
        }
        val permissionList = ArrayList<String>();
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {//添加为申请的权限
                permissionList.add(permission)
            }
        }
        if (permissionList.isNotEmpty()) {
            startRequestPermission(permissionList)
        } else {
            this.permissionListener(true, null)
        }
    }

    /**
     * 申请未获取到的权限
     */
    private fun startRequestPermission(permissionList: ArrayList<String>) {
        var rationale = false  //是否显示申请理由提示框
        //如果有拒绝则提示申请理由提示框，否则直接向系统请求权限
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return
        }
        for (permission in permissionList) {
            rationale = rationale || shouldShowRequestPermissionRationale(permission)
        }
        val permissions = Array<String>(permissionList.size) { _ -> "" }
        permissionList.toArray(permissions)
        if (rationale) {//显示拒绝提示框
            showRationalDialog(permissions)
        } else {
            requestPermissions(permissions, REQUEST_CODE)
        }
    }

    /**
     *拒绝提示框
     */
    @Synchronized
    private fun showRationalDialog(permissions: Array<String>) {
        android.app.AlertDialog.Builder(this)
            .setMessage(DEF_RATIONAL_MESSAGE)
            .setCancelable(false)
            .setNegativeButton(
                DEF_DENIED_CLOSE_BTN_TEXT
            ) { _: DialogInterface?, _: Int -> finish() }
            .setPositiveButton(
                DEF_RATIONAL_BTN_TEXT
            ) { _: DialogInterface?, _: Int -> startSetting() }.show()
    }

    /**
     * 跳转到设置界面
     */
    private fun startSetting() {
        try {
            val intent =
                Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    .setData(Uri.parse("package:$packageName"))
            startActivityForResult(intent, REQUEST_CODE_SETTING)
        } catch (e: ActivityNotFoundException) {
            e.printStackTrace()
            try {
                val intent =
                    Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS)
                startActivityForResult(intent, REQUEST_CODE_SETTING)
            } catch (e1: Exception) {
                e1.printStackTrace()
            }
        }
    }

    /**
     * 拒绝权限提示框
     *
     * @param permissions
     */
    @Synchronized
    private fun showDeniedDialog(permissions: Array<String>) {
        android.app.AlertDialog.Builder(this)
            .setMessage(DEF_DENIED_MESSAGE)
            .setCancelable(false)
            .setNegativeButton(
                DEF_DENIED_CLOSE_BTN_TEXT
            ) { _, _ ->
                permissionListener(false, permissions)
            }
            .setPositiveButton(
                DEF_RATIONAL_BTN_TEXT
            ) { _, _ -> startSetting() }.show()
    }

    inner class ForceOfflineReceiver : BroadcastReceiver() {
        override fun onReceive(p0: Context, p1: Intent?) {
            AlertDialog.Builder(p0).apply {
                setTitle("警告")
                setMessage("确定要退出软件？")
                setCancelable(false)
                setPositiveButton("确定") { _, _ ->
                    ActivityCollector.finishAll()
                }
                show()
            }
        }
    }
}