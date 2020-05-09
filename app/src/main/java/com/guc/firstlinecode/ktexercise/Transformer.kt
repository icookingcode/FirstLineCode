package com.guc.firstlinecode.ktexercise

/**
 * Created by guc on 2020/5/9.
 * 描述：泛型类逆变
 */
interface Transformer<in T> {
    fun transform(t: T): String
}