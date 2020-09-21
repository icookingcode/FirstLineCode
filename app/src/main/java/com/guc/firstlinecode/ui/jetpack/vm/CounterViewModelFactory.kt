package com.guc.firstlinecode.ui.jetpack.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by guc on 2020/6/1.
 * 描述：传输传递需要自定义factory
 */
class CounterViewModelFactory(private val counterReserved: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CounterViewModel(counterReserved) as T
    }
}