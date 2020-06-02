package com.guc.firstlinecode.ui.jetpack.vm

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.guc.firstlinecode.R
import com.guc.firstlinecode.base.BaseActivity
import com.guc.firstlinecode.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_view_model.*

/**
 * ViewModel 使用
 */
class ViewModelActivity : BaseActivity() {
    lateinit var counterViewModel: CounterViewModel
    lateinit var counterViewModel2: CounterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)
        counterViewModel = ViewModelProvider.AndroidViewModelFactory(application)
            .create(CounterViewModel::class.java)
        counterViewModel2 = ViewModelProvider(
            this.viewModelStore,
            CounterViewModelFactory(3)
        ).get(CounterViewModel::class.java)

        plusOne.setOnClickListener {
            counterViewModel2.counter++
            refreshCounter()
        }
        refreshCounter()
        ToastUtil.toast(this, "页面被创建")


    }

    private fun refreshCounter() {
        plusOne.text = counterViewModel2.counter.toString()
    }
}
