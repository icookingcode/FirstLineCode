package com.guc.firstlinecode.ui.jetpack.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.guc.firstlinecode.bean.News
import kotlin.concurrent.thread

/**
 * Created by guc on 2020/6/2.
 * 描述：包含LiveData数据的ViewModel
 * LiveData数据变化时通知观察者
 */
class DataViewModel(countReserved: Int) : ViewModel() {
    val counter: LiveData<Int> //只暴露不可变的LiveData给外部，非ViewModel中不能给LiveData设置数据
        get() = _counter
    private val _counter = MutableLiveData<Int>() //

    private val _newsLiveData = MutableLiveData<News>()
    val news = Transformations.map(_newsLiveData) { user ->
        "${user.title}:${user.content}"
    }
    val newsSm: LiveData<String> = Transformations.switchMap(_newsLiveData) { news ->
        setNews2(news)

    }

    fun setNews2(news: News): LiveData<String> {
        _newsLiveData.value = news
        return this.news
    }

    init {
        _counter.value = countReserved
    }

    fun plusOne() {
        val count = _counter.value ?: 0
        thread {
            Thread.sleep(1000)
            _counter.postValue(count + 1)
        }
//        counter.value = count + 1
    }

    fun setNews(news: News) {
        _newsLiveData.value = news
    }

    fun clear() {
        _counter.value = 0
    }

}