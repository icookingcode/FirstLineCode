package com.guc.firstlinecode.utils

import android.content.Context
import android.content.SharedPreferences
import org.jetbrains.annotations.NotNull

/**
 * Created by guc on 2020/5/6.
 * 描述：SharedPreferences工具封装
 */
object SharedPreferencesUtils {
    private lateinit var preferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    fun init(context: Context, name: String, mode: Int = Context.MODE_PRIVATE) {
        preferences = context.getSharedPreferences(name, mode)
        editor = preferences.edit()
    }

    fun put(key: String, value: Any) {
        if (isInit()) {
            editor.put(key, value)
            editor.apply()
        } else {
            throw Exception("not init,please use SharedPreferencesUtils.init()")
        }
    }

    fun <T> getNotString(key: String, @NotNull default: T): T {
        if (isInit()) {
            return when (default) {
                is Boolean -> preferences.getBoolean(key, default) as T
                is Float -> preferences.getFloat(key, default) as T
                is Int -> preferences.getInt(key, default) as T
                is Long -> preferences.getLong(key, default) as T
                null -> throw IllegalArgumentException("default value can not be null")
                else -> preferences.getString(key, null) as T
            }
        } else {
            throw Exception("not init,please use SharedPreferencesUtils.init()")
        }
    }

    fun getString(key: String, default: String? = null): String? {
        if (isInit()) {
            return preferences.getString(key, default)
        } else {
            throw Exception("not init,please use SharedPreferencesUtils.init()")
        }
    }

    fun clear() {
        if (isInit()) {
            editor.clear()
            editor.apply()
        } else {
            throw Exception("not init,please use SharedPreferencesUtils.init()")
        }
    }

    private fun isInit(): Boolean {
        return ::preferences.isInitialized
    }
}