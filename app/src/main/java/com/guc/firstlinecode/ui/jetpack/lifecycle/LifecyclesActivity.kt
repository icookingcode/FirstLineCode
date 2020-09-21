package com.guc.firstlinecode.ui.jetpack.lifecycle

import android.os.Bundle
import com.guc.firstlinecode.R
import com.guc.firstlinecode.base.BaseActivity
import kotlinx.android.synthetic.main.activity_jetpack.*

/**
 * Lifecycle 感知Activity生命周期
 */
class LifecyclesActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycles)
        titleLayout.title = "Lifecycle"
        lifecycle.addObserver(MyObserver())
    }
}
