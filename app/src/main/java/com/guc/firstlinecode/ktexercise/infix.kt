package com.guc.firstlinecode.ktexercise

/**
 * Created by guc on 2020/5/9.
 * 描述：infix函数学习
 */
//A.method(B)  等价于 A method B
infix fun String.beginWith(prefix: String) = startsWith(prefix)

infix fun <T> Collection<T>.has(t: T) = contains(t)

//A with B  等价于 A to B
infix fun <T, V> T.with(that: V) = Pair(this, that)

fun main(args: Array<String>) {
    val string = "HelloWorld"
    val prefix = "Hello"
    val boolean = string.beginWith(prefix)
    val boolean2 = string beginWith prefix
    println("$string 以 $prefix 开头 = $boolean")
}