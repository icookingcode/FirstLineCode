package com.guc.firstlinecode.ktexercise

/**
 * Created by guc on 2020/4/30.
 * 描述：
 */
class Student(name: String, val age: Int) : Person(name, age) {
    init {
        println("init in Student ")
    }

    constructor(name: String) : this(name, 0) {
        println("constructor in Student ")
    }

    private val names = ArrayList<String>()
    override fun print() {
        println("print in Student")
//        names.apply {
//            add("Hello1")
//            add("Hello2")
//            add("Hello3")
//            add("Hello4")
//        }
    }
}

fun main(args: Array<String>) {
    val student = Student("guc")
    val data = SimpleData<Student>(student)
    handleData(data)

    val transformer = object : Transformer<Person> {
        override fun transform(t: Person): String {
            return "${t.name}"
        }
    }
    handleTransformer(transformer)
}

fun handleTransformer(trans: Transformer<Student>) {

    val student = Student("谷超")
    val result = trans.transform(student)
    println("transformer:$result")
}

fun handleData(data: SimpleData<Person>) {
    val personData = data.get() as Student
    println(personData.name)
}