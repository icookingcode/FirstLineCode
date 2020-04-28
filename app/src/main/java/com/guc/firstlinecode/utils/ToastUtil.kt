package com.guc.firstlinecode.utils

import android.content.Context
import android.widget.Toast

/**
 * Created by guc on 2020/4/28.
 * 描述：Toast 封装
 */
object ToastUtil {
    fun toast(context: Context, txt: String, time: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, txt, time).show()
    }
}