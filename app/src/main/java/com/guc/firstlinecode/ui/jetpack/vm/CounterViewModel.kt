package com.guc.firstlinecode.ui.jetpack.vm

import androidx.lifecycle.ViewModel

/**
 * Created by guc on 2020/6/1.
 * 描述：计数器
 */
class CounterViewModel(counterReserved: Int = 0) : ViewModel() {
    var counter = counterReserved
}