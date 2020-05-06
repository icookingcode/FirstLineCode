package com.guc.firstlinecode.utils

import android.content.Context
import android.content.SharedPreferences

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

    fun putString(key: String, value: String) {
        if (isInit()) {
            editor.putString(key, value)
            editor.apply()
        } else {
            throw Exception("not init,please use SharedPreferencesUtils.init()")
        }
    }

    fun getString(key: String): String? {
        return preferences.getString(key, null)
    }

    fun putInt(key: String, value: Int) {
        if (isInit()) {
            editor.putInt(key, value)
            editor.apply()
        } else {
            throw Exception("not init,please use SharedPreferencesUtils.init()")
        }
    }

    fun getInt(key: String, default: Int = 0): Int {
        return preferences.getInt(key, default)
    }

    fun putBoolean(key: String, value: Boolean) {
        if (isInit()) {
            editor.putBoolean(key, value)
            editor.apply()
        } else {
            throw Exception("not init,please use SharedPreferencesUtils.init()")
        }
    }

    fun getBoolean(key: String, default: Boolean = false): Boolean {
        return preferences.getBoolean(key, default)
    }

    fun putFloat(key: String, value: Float) {
        if (isInit()) {
            editor.putFloat(key, value)
            editor.apply()
        } else {
            throw Exception("not init,please use SharedPreferencesUtils.init()")
        }
    }

    fun getFloat(key: String, default: Float = 0f): Float {
        return preferences.getFloat(key, default)
    }

    fun putLong(key: String, value: Long) {
        if (isInit()) {
            editor.putLong(key, value)
            editor.apply()
        } else {
            throw Exception("not init,please use SharedPreferencesUtils.init()")
        }
    }

    fun getLong(key: String, default: Long = 0): Long {
        return preferences.getLong(key, default)
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