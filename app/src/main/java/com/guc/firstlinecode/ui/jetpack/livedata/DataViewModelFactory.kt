package com.guc.firstlinecode.ui.jetpack.livedata

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by guc on 2020/6/1.
 * 描述：传输传递需要自定义factory
 */
class DataViewModelFactory(private val counterReserved: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DataViewModel(counterReserved) as T
    }
}