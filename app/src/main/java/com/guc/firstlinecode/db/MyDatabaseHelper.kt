package com.guc.firstlinecode.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.guc.firstlinecode.utils.ToastUtil

/**
 * Created by guc on 2020/5/6.
 * 描述：SQLite数据库
 */
class MyDatabaseHelper(val context: Context) : SQLiteOpenHelper(context, DB_NAME, null, VERSION) {
    companion object {
        const val TAG = "MyDatabaseHelper"
        const val DB_NAME = "BookStore.db"
        const val VERSION = 2
        const val TAB_BOOK = "Book"
        const val TAB_CATEGORY = "Category"
    }

    private val createBook = "create table $TAB_BOOK (" +
            " id integer primary key autoincrement," +
            "author text," +
            "price real," +
            "pages integer," +
            "name text)," +
            "category_id integer"
    private val createCategory = "create table $TAB_CATEGORY(" +
            "id integer primary key autoincrement," +
            "category_name text," +
            "category_code integer)"

    override fun onCreate(p0: SQLiteDatabase) {
        p0.execSQL(createBook)
        p0.execSQL(createCategory)
        ToastUtil.toast(context, "Create Succeeded")
    }

    override fun onUpgrade(p0: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
//        p0.execSQL("drop table if exists Book")
//        p0.execSQL("drop table if exists Category")
//        onCreate(p0)
        if (oldVersion <= 1) { //第二个版本添加分类表
            p0.execSQL(createCategory)
        }
        if (oldVersion <= 2) {//第三个版本Book表添加category_id字段
            p0.execSQL("alter table $TAB_BOOK add column category_id integer")
        }
    }
}