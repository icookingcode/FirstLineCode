package com.guc.firstlinecode.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.content.UriMatcher.NO_MATCH
import android.database.Cursor
import android.net.Uri
import com.guc.firstlinecode.db.MyDatabaseHelper

/**
 * Created by guc on 2020/5/7.
 * 描述：图书provider
 */
class BookProvider : ContentProvider() {


    private var dbHelper: MyDatabaseHelper? = null

    private val uriMatcher by lazy {
        val matcher = UriMatcher(NO_MATCH)
        matcher.addURI(
            BookContract.AUTHORITY,
            MyDatabaseHelper.TAB_BOOK,
            BookContract.BOOK_DIR
        )
        matcher.addURI(
            BookContract.AUTHORITY,
            "${MyDatabaseHelper.TAB_BOOK}/#",
            BookContract.BOOK_ITEM
        )
        matcher
    }

    /**
     * 初始化ContentProvider时调用。通常会完成数据库的创建和升级等操作，返回true则初始化成功，false则失败
     */
    override fun onCreate(): Boolean = context?.let {
        dbHelper = MyDatabaseHelper(it)
        true
    } ?: false

    override fun insert(uri: Uri, values: ContentValues?): Uri? = dbHelper?.let {
        val db = it.writableDatabase
        val uriReturn = when (uriMatcher.match(uri)) {
            BookContract.BOOK_DIR, BookContract.BOOK_ITEM -> {
                val bookId = db.insert(MyDatabaseHelper.TAB_BOOK, null, values)
                Uri.withAppendedPath(BookContract.CONTENT_URI, "$bookId")
            }
            else -> null
        }
        uriReturn
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? = dbHelper?.let {
        val db = it.readableDatabase
        val cursor = when (uriMatcher.match(uri)) {
            BookContract.BOOK_DIR -> {//查询表中所有数据
                db.query(
                    MyDatabaseHelper.TAB_BOOK,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    sortOrder
                )
            }
            BookContract.BOOK_ITEM -> {//查询单条数据
                val bookId = uri.pathSegments[1]
                db.query(
                    MyDatabaseHelper.TAB_BOOK,
                    projection,
                    "id=?",
                    arrayOf(bookId),
                    null,
                    null,
                    sortOrder
                )
            }
            else -> null
        }
        cursor
    }


    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int = dbHelper?.let {
        val db = it.writableDatabase
        val updateRows = when (uriMatcher.match(uri)) {
            BookContract.BOOK_DIR -> db.update(
                MyDatabaseHelper.TAB_BOOK,
                values,
                selection,
                selectionArgs
            )
            BookContract.BOOK_ITEM -> db.update(
                MyDatabaseHelper.TAB_BOOK,
                values,
                "id=?",
                arrayOf(uri.pathSegments[1])
            )
            else -> 0
        }
        updateRows
    } ?: 0

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int =
        dbHelper?.let {
            val db = it.writableDatabase
            val deleteRows = when (uriMatcher.match(uri)) {
                BookContract.BOOK_DIR -> db.delete(
                    MyDatabaseHelper.TAB_BOOK,
                    selection,
                    selectionArgs
                )
                BookContract.BOOK_ITEM -> db.delete(
                    MyDatabaseHelper.TAB_BOOK,
                    "id=?",
                    arrayOf(uri.pathSegments[1])
                )
                else -> 0
            }
            deleteRows
        } ?: 0

    override fun getType(uri: Uri): String? = when (uriMatcher.match(uri)) {
        BookContract.BOOK_DIR -> "vnd.android.cursor.dir/vnd.${BookContract.AUTHORITY}.${MyDatabaseHelper.TAB_BOOK}"
        BookContract.BOOK_ITEM -> "vnd.android.cursor.item/vnd.${BookContract.AUTHORITY}.${MyDatabaseHelper.TAB_BOOK}"
        else -> null

    }
}