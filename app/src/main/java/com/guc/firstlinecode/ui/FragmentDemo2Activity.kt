package com.guc.firstlinecode.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.guc.firstlinecode.R
import com.guc.firstlinecode.base.BaseActivity
import com.guc.firstlinecode.ui.fragment.LeftFragment

class FragmentDemo2Activity : BaseActivity() {
    private lateinit var leftFragment: LeftFragment

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, FragmentDemo2Activity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_demo2)
        supportActionBar?.hide()
    }
}
