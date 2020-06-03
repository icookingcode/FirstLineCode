package com.guc.firstlinecode.bean

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.guc.firstlinecode.db.MyDatabaseHelper

/**
 * Created by guc on 2020/6/3.
 * 描述：room实体类
 */
@Entity(tableName = MyDatabaseHelper.TAB_BOOK)
data class BookEntity(var name: String, var author: String, var pages: Int, var price: Float) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}