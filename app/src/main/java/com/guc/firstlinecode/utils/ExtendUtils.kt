package com.guc.firstlinecode.utils

import android.content.ContentValues
import android.content.SharedPreferences
import kotlin.reflect.KProperty

/**
 * Created by guc on 2020/5/6.
 * 描述：扩展工具类
 */
//KTX也提供了该方法
fun cvOf(vararg pairs: Pair<String, Any?>) = ContentValues().apply {
    for (pair in pairs) {
        val key = pair.first
        when (val value = pair.second) {
            is Int -> put(key, value)
            is Long -> put(key, value)
            is Short -> put(key, value)
            is Float -> put(key, value)
            is Double -> put(key, value)
            is Boolean -> put(key, value)
            is String -> put(key, value)
            is Byte -> put(key, value)
            is ByteArray -> put(key, value)
            null -> putNull(key)
        }
    }
}

fun SharedPreferences.open(block: SharedPreferences.Editor.() -> Unit) {
    val editor = edit()
    editor.block()
    editor.apply()
}

fun SharedPreferences.Editor.put(key: String, value: Any): SharedPreferences.Editor {
    when (value) {
        is String -> putString(key, value)
        is Boolean -> putBoolean(key, value)
        is Float -> putFloat(key, value)
        is Int -> putInt(key, value)
        is Long -> putLong(key, value)
    }
    return this
}

//作用等同于obj.apply{}
inline fun <T> T.build(block: T.() -> Unit): T {
    block()
    return this
}

//作用等同于 by lazy
//不完善，项目中懒加载还使用by lazy
fun <T> later(block: () -> T) = Later(block)

class Later<T>(val block: () -> T) {
    var value: Any? = null
    operator fun getValue(any: Any?, prop: KProperty<*>): T {
        if (value == null) {
            value = block()
        }
        return value as T
    }
}
