package com.guc.firstlinecode.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import java.io.File

/**
 * Created by guc on 2020/5/22.
 * 描述：工具方法
 */
/**
 * 求最大值
 */
fun <T : Comparable<T>> max(vararg nums: T): T {
    if (nums.isEmpty()) throw RuntimeException("Params can not be empty.")
    var maxNum = nums[0]
    for (num in nums) {
        if (num > maxNum) maxNum = num
    }
    return maxNum
}

fun installApk(file: File, context: Context) {
    //新下载apk文件存储地址
    val intent = Intent(Intent.ACTION_VIEW);
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
    intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
    intent.setDataAndType(
        FileUtils.getUri(context, "${context.packageName}.fileprovider", file),
        "application/vnd.android.package-archive"
    );
    context.startActivity(intent);
}