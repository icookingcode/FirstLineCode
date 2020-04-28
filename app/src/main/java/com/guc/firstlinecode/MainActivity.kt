package com.guc.firstlinecode

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.guc.firstlinecode.base.BaseActivity
import com.guc.firstlinecode.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button1.setOnClickListener {
            val intent = Intent(this, SingleInstanceActivity::class.java)
            startActivity(intent)
        }

        button2.setOnClickListener {
            val intent = Intent("com.guc.firstlinecode.action.ACTION_FISRTACTIVITY")
            startActivity(intent)
        }

        button3.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.baidu.com")
            intent.addCategory(Intent.CATEGORY_BROWSABLE)
            startActivity(intent)
        }
        button4.setOnClickListener {
            DialogActivity.start(this)
        }
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

}
