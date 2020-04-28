package com.guc.firstlinecode

import android.os.Bundle
import com.guc.firstlinecode.base.BaseActivity
import kotlinx.android.synthetic.main.activity_single_instance.*

class SingleInstanceActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_instance)
        button.setOnClickListener {
            FirstActivity.start(this)
        }
    }
}
