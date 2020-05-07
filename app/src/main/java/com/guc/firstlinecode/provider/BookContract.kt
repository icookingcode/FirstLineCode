package com.guc.firstlinecode.provider

import android.net.Uri
import com.guc.firstlinecode.db.MyDatabaseHelper


/**
 * Created by guc on 2020/5/7.
 * 描述：ContentProvider约束
 */
class BookContract {
    companion object {
        const val AUTHORITY = "com.guc.firstlinecode.provider"
        val AUTHORITY_URI = Uri.parse("content://$AUTHORITY")
        val CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, MyDatabaseHelper.TAB_BOOK)
        const val BOOK_DIR = 0
        const val BOOK_ITEM = 1
        const val BOOK_NAME = "name"
        const val BOOK_AUTHOR = "author"
        const val BOOK_PRICE = "price"
        const val BOOK_PAGES = "pages"
    }
}