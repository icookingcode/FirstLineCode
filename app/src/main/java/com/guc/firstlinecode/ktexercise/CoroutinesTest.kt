package com.guc.firstlinecode.ktexercise

import kotlinx.coroutines.*

/**
 * Created by guc on 2020/5/20.
 * 描述：协程基本用法示例
 */
class CoroutinesTest {

}

suspend fun foo(tag: String = "First") {
    println("$tag  = 1")
    delay(100)
    println("$tag  = 2")
    delay(100)
    println("$tag  = 3")
    delay(100)
    println("$tag  = 4")
}

suspend fun bar(tag: String = "First") {
    println("$tag  = 5")
    delay(100)
    println("$tag  = 6")
    delay(100)
    println("$tag  = 7")
    delay(100)
    println("$tag  = 8")
}

suspend fun printDot() = coroutineScope {
    launch {
        println(".")
        delay(100)
    }
}

suspend fun <T> getAsyncResult(deferred: Deferred<T>): T {
    return deferred.await()
}

suspend fun main(args: Array<String>) {


    runBlocking {//保证在协程作用域内的所有代码和子协程全部执行完
        println("codes run in coroutine runBlocking scope")
        delay(1500)//让当前协程延迟指定时间后再运行
        println("codes run in coroutine runBlocking scope finished")

        launch {
            foo()
            bar()
        }

        launch {
            foo("Second")
            bar("Second")
        }

        val result = async {
            5 + 5
            "Hello"
        }.await()
        println(result)

        val result2 = withContext(Dispatchers.Default) {
            5 + 6
        }
        println(result2)
    }

    GlobalScope.launch {
        println("codes run in coroutine scope")
        delay(1500)//让当前协程延迟指定时间后再运行
        println("codes run in coroutine scope finished")
    }
    Thread.sleep(1000)//阻塞当前线程


    //项目常用协程写法
    //创建job对象
    val job = Job()
    //通过CoroutineScope()函数获取CoroutineScope对象
    val scope = CoroutineScope(job)
    //创建的协程都会被关联到Job对象的作用域
    scope.launch {
        //处理具体逻辑
    }
    scope.launch {

    }
    var result = scope.async {
        5 + 6
    }
    println(getAsyncResult(result))
    //取消job作用域下的所有协程
    job.cancel()

}