package com.guc.firstlinecode.ktexercise

/**
 * Created by guc on 2020/4/30.
 * 描述：
 */
abstract class Person(open var name: String) {

    init {
        println("init in Person")
    }

    constructor(name: String, age: Int) : this(name) {
        println("constructor in Person")
        print()
    }

    abstract fun print()
}