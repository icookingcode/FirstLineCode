package com.guc.firstlinecode.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.guc.firstlinecode.bean.BookEntity
import com.guc.firstlinecode.dao.BookDao

/**
 * Created by guc on 2020/6/3.
 * 描述：database
 */
@Database(version = 2, entities = [BookEntity::class])
abstract class BookDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao

    companion object {
        private var instance: BookDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): BookDatabase {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(
                context.applicationContext,
                BookDatabase::class.java,
                MyDatabaseHelper.DB_NAME //数据库名
            ).build().apply { instance = this }
        }
    }
}