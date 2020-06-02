package com.guc.firstlinecode.ui.jetpack

import android.os.Bundle
import android.view.View
import com.guc.firstlinecode.R
import com.guc.firstlinecode.base.BaseActivity
import com.guc.firstlinecode.ui.jetpack.lifecycle.LifecyclesActivity
import com.guc.firstlinecode.ui.jetpack.vm.ViewModelActivity
import com.guc.firstlinecode.utils.quickStartActivity
import kotlinx.android.synthetic.main.activity_jetpack.*


/**
 * Jetpack 组件库
 */
class JetpackActivity : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jetpack)
        titleLayout.title = "Jetpack 组件库"
        btnViewModel.setOnClickListener(this)
        btnLifecycle.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnViewModel -> quickStartActivity<ViewModelActivity>(this) {}
            R.id.btnLifecycle -> quickStartActivity<LifecyclesActivity>(this) {}
        }
    }
}
