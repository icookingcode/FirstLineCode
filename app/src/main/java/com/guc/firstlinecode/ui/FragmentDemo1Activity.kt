package com.guc.firstlinecode.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.guc.firstlinecode.R
import com.guc.firstlinecode.base.BaseActivity
import com.guc.firstlinecode.ui.fragment.AnotherRightFragment
import com.guc.firstlinecode.ui.fragment.LeftFragment
import com.guc.firstlinecode.ui.fragment.RightFragment
import com.guc.firstlinecode.utils.ScreenUtil
import kotlinx.android.synthetic.main.activity_fragment_demo1.*
import kotlinx.android.synthetic.main.left_fragment.*

class FragmentDemo1Activity : BaseActivity() {
    private lateinit var leftFragment: LeftFragment

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, FragmentDemo1Activity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_demo1)
        supportActionBar?.hide()
        if (ScreenUtil.isPad(this)) {
            replaceFragment(R.id.rightLayout, RightFragment())
            buttonReplace.setOnClickListener {
                replaceFragment(R.id.rightLayout, AnotherRightFragment(), true)
            }
        } else {
            buttonReplace.visibility = View.GONE
            rightLayout.visibility = View.GONE
        }

        leftFragment = leftFrag as LeftFragment   //通过布局文件中定义的Fragment的id直接获取
//        leftFragment = supportFragmentManager.findFragmentById(R.id.leftFrag) as LeftFragment  //通过SupportFragmentManager获取（不推荐）
    }

    private fun replaceFragment(
        containerId: Int,
        fragment: Fragment,
        isAddToBackStack: Boolean = false
    ) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(containerId, fragment)
        if (isAddToBackStack) transaction.addToBackStack(null)
        transaction.commit()
    }
}
