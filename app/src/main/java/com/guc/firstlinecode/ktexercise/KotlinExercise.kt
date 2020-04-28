package com.guc.firstlinecode.ktexercise

/**
 * Created by guc on 2020/4/27.
 * 描述：基本语法练习
 */
val plus: (Int, Int) -> Int = { a: Int, b: Int -> a + b } //Lambda表达式

//除法
val division: (Int, Int) -> Int? = { a: Int, b: Int -> if (b == 0) null else a / b }

val plus2 = { a: Int, b: Int -> a + b } //可省略类型

fun main(args: Array<String>) {
    println("FirstLineCode")
    val res = plus(3, 4)
    println("3+4=$res")
    val res4 = plus4(5, 4)
    println("5+4=$res4")

    val res5 = operation(5, 6) { p1, p2 -> p1 * p2 }
    println("5*6=$res5")

    val res6 = operation(5, 0, division)
    println("5/0 = $res6")

    val res7 = getStringLength("guchao")
    val res8 = getStringLength(null)
    println("guchao.length = $res7\nnull.length = $res8")

    val res9 = getUserInfo("guchao", 30, address = "焦作沁阳市")
    val res10 = getUserInfo("lily", 29, "female", "America")
    println(res9)
    println(res10)

    val list = listOf("Apple", "Banana", "Orange")
    val res11 = with(StringBuilder()) {
        for (i in list) {
            append(i).append("\n")
        }
        toString()
    }
    println(res11)
    val res12 = StringBuilder().run {
        for (i in list) {
            append(i).append("\n")
        }
        toString()
    }
    println(res12)
    val res13 = StringBuilder().apply {
        for (i in list) {
            append(i).append("\n")
        }
    }
    println(res13.toString())
}

fun plus3(a: Int, b: Int): Int {
    return a + b
}

//plus3 简写 ：方法体只有一句时可用 = 直接返回
fun plus4(a: Int, b: Int) = a + b

/*
Lambda 表达式作为参数
 */
fun operation(p1: Int, p2: Int, lambda: (Int, Int) -> Int?): Int? {
    return lambda(p1, p2)
}

/**
 * 获取字符串的长度
 * s ==null  返回0
 */
fun getStringLength(s: String?): Int = s?.length ?: 0

/**
 * 获取字符串的长度
 * s ==null  抛异常
 */
fun getStringLength2(s: String?): Int = s!!.length

/**
 * 默认参数 可选
 */
fun getUserInfo(name: String, age: Int, sex: String = "male", address: String) =
    "姓名：$name  年龄：$age  性别：$sex  地址：$address"


