package com.guc.firstlinecode.utils

/**
 * Created by guc on 2020/4/30.
 * 描述：String 类扩展
 */

/**
 * 重新定义字符串的乘法
 */
operator fun String.times(n: Int) = repeat(n)

/**
 * 扩展函数-统计字符串中字母的个数
 */
fun String.letterCount(): Int {
    var count = 0
    for (char in this) {
        if (char.isLetter()) count++
    }
    return count
}