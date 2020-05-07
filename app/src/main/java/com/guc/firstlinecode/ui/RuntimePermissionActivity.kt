package com.guc.firstlinecode.ui

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.guc.firstlinecode.R
import com.guc.firstlinecode.base.BaseActivity
import com.guc.firstlinecode.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_runtime_permission.*

class RuntimePermissionActivity : BaseActivity(), View.OnClickListener {
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var contactList: ArrayList<String>

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
        btnPickPhone.setOnClickListener(this)
        contactList = ArrayList()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, contactList)
        lvContacts.adapter = adapter
        lvContacts.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            tvShow.text = contactList[position]
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnMakeCall -> makeCall2()
            R.id.btnPickPhone -> pickPhone()
        }
    }

    //选择联系人
    private fun pickPhone() {
        requestRuntimePermissions(
            arrayOf(
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.WRITE_CONTACTS
            )
        ) { bool, _ ->
            ToastUtil.toast(context, (if (bool) "申请成功" else "已拒绝"))
            if (bool) readContacts()
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

    private fun readContacts() {
        contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null
        )?.apply {
            contactList.clear()
            while (moveToNext()) {
                val displayName =
                    getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))//姓名
                val number =
                    getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))//电话
                contactList.add("$displayName\n$number")
            }
            adapter.notifyDataSetChanged()
            close()
        }

    }

}
