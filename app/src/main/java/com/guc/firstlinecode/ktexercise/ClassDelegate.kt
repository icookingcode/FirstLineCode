package com.guc.firstlinecode.ktexercise

/**
 * Created by guc on 2020/5/7.
 * 描述：类委托
 */
//创建接口
interface Base {
    fun print()
}

//创建被委托的类
class BaseImpl(val x: Int) : Base {
    override fun print() {
        print(x)
    }
}

// 通过关键字 by 建立委托类
class Derived(b: Base) : Base by b

fun main(args: Array<String>) {
    val b = BaseImpl(110)
    Derived(b).print() // 输出 110
}

