package com.guc.firstlinecode.utils

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