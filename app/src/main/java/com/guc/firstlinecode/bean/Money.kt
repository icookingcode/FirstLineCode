package com.guc.firstlinecode.bean

/**
 * Created by guc on 2020/4/30.
 * 描述：运算符重载
 */
class Money(val value: Int) {
    operator fun plus(money: Money): Money {
        return Money(value + money.value)
    }
}