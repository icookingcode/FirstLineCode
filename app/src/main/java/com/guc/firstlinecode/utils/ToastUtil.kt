package com.guc.firstlinecode.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

/**
 * Created by guc on 2020/4/28.
 * 描述：Toast 封装
 */
object ToastUtil {
    fun toast(context: Context, txt: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, txt, duration).show()
    }

    fun snack(
        view: View,
        txt: String,
        txtAction: String = "取消",
        time: Int = Snackbar.LENGTH_SHORT,
        back: (View) -> Unit
    ) {
        Snackbar.make(view, txt, time).setAction(txtAction) { back(it) }.show()
    }
}