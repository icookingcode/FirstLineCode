package com.guc.firstlinecode.ktexercise

/**
 * Created by guc on 2020/5/9.
 * 描述：泛型类协变
 */
class SimpleData<out T>(val data: T?) {
    fun get(): T? = data
}