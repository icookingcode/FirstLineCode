package com.guc.firstlinecode.ktexercise

/**
 * Created by guc on 2020/4/30.
 * 描述：
 */
class Student(val name: String, val age: Int) : Person(name, age) {
    init {
        println("init in Student ")
    }

    constructor(name: String) : this(name, 0) {
        println("constructor in Student ")
    }

    private val names = ArrayList<String>()
    override fun print() {
        println("print in Student")
        names.apply {
            add("Hello1")
            add("Hello2")
            add("Hello3")
            add("Hello4")
        }
    }
}

fun main(args: Array<String>) {
    val student = Student("guc")

}