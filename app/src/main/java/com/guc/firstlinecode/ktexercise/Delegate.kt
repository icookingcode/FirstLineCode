package com.guc.firstlinecode.ktexercise

import kotlin.reflect.KProperty

/**
 * Created by guc on 2020/5/7.
 * 描述：属性委托
 */
class Delegate {
    var propValue: Any? = "default"

    /**
     * [any] 指定任何类都可以使用该代理
     * [prop] 属性操作类，可获取各种属性相关的值
     */
    operator fun getValue(any: Any?, prop: KProperty<*>): Any? {
        return propValue
    }

    /**
     * [any] 指定任何类都可以使用该代理
     * [prop] 属性操作类，可获取各种属性相关的值
     * [value] 赋给委托属性的值
     */
    operator fun setValue(any: Any?, prop: KProperty<*>, value: Any?) {
        propValue = value
    }
}