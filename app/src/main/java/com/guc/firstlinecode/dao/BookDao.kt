package com.guc.firstlinecode.dao

import androidx.room.*
import com.guc.firstlinecode.bean.BookEntity

/**
 * Created by guc on 2020/6/3.
 * 描述：数据访问对象
 */
@Dao
interface BookDao {
    @Insert
    fun insertBook(bookEntity: BookEntity): Long

    @Update
    fun updateBook(bookEntity: BookEntity)

    @Query("select * from Book")
    fun loadAllBooks(): List<BookEntity>

    @Delete
    fun deleteBook(bookEntity: BookEntity)

    @Query("delete from Book where id = :id")
    fun deleteBookById(id: Long): Int
}