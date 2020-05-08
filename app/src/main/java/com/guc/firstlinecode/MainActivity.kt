package com.guc.firstlinecode

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.OnClickListener
import com.guc.firstlinecode.base.BaseActivity
import com.guc.firstlinecode.ui.*
import com.guc.firstlinecode.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
        button5.setOnClickListener(this)
        button6.setOnClickListener(this)
        button7.setOnClickListener(this)
        button8.setOnClickListener(this)
        button9.setOnClickListener(this)
        button10.setOnClickListener(this)
        button11.setOnClickListener(this)
        button12.setOnClickListener(this)
        button13.setOnClickListener(this)
        button14.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_item -> ToastUtil.toast(this, "添加")
            R.id.remove_item -> ToastUtil.toast(this, "移除")
        }
        return true
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button1 -> SingleInstanceActivity.start(this)
            R.id.button2 -> startActivity(Intent("com.guc.firstlinecode.action.ACTION_FISRTACTIVITY"))
            R.id.button3 -> {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("https://www.baidu.com")
                intent.addCategory(Intent.CATEGORY_BROWSABLE)
                startActivity(intent)
            }
            R.id.button4 -> DialogActivity.start(this)
            R.id.button5 -> ListViewActivity.start(this)
            R.id.button6 -> RecyclerViewActivity.start(this)
            R.id.button7 -> FragmentDemo1Activity.start(this)
            R.id.button8 -> FragmentDemo2Activity.start(this)
            R.id.button9 -> NewsActivity.start(this)
            R.id.button10 -> BroadcastActivity.start(this)
            R.id.button11 -> DataPersistenceActivity.start(this)
            R.id.button12 -> RuntimePermissionActivity.start(this)
            R.id.button13 -> NotificationActivity.start(this)
            R.id.button14 -> CameraAndAlbumActivity.start(this)
        }
    }

}
