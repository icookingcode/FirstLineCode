package com.guc.firstlinecode.utils

import android.content.Context
import android.content.res.Configuration

/**
 * Created by guc on 2020/4/29.
 * 描述：屏幕工具
 */
object ScreenUtil {
    //判断当前设备是否是平板
    fun isPad(context: Context): Boolean =
        (context.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE

    //判断当前主题样式 true:Night false:Day
    fun isDarkTheme(context: Context): Boolean =
        (context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES
}