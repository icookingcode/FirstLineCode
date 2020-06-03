package com.guc.firstlinecode.bean

import java.io.Serializable

/**
 * Created by guc on 2020/5/6.
 * 描述：书
 */
class Book(id: Long, name: String, author: String, pages: Int, price: Float) : Serializable {
    var id: Long = 0
    var name: String = ""
    var author: String = ""
    var pages: Int = 0
    var price: Float = 0.0f

    init {
        this.id = id
        this.name = name
        this.author = author
        this.pages = pages
        this.price = price
    }

    override fun toString(): String {
        return "id =$id, name =$name, author =$author, pages =$pages, price =$price "
    }
}