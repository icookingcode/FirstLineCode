package com.guc.firstlinecode.ui

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import androidx.core.content.contentValuesOf
import com.guc.firstlinecode.R
import com.guc.firstlinecode.base.BaseActivity
import com.guc.firstlinecode.bean.Book
import com.guc.firstlinecode.db.MyDatabaseHelper
import com.guc.firstlinecode.utils.FileUtils
import com.guc.firstlinecode.utils.LogG
import com.guc.firstlinecode.utils.SharedPreferencesUtils
import com.guc.firstlinecode.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_broadcast.titleLayout
import kotlinx.android.synthetic.main.activity_data_persistence.*

/**
 * 数据持久化
 */
class DataPersistenceActivity : BaseActivity(), View.OnClickListener {
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, DataPersistenceActivity::class.java))
        }
    }

    private lateinit var sqLiteDatabase: SQLiteDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_persistence)
        titleLayout.title = "数据持久化"
        SharedPreferencesUtils.init(this, "data")
        btnSave2File.setOnClickListener(this)
        btnReadFile.setOnClickListener(this)
        btnReadAssetsFile.setOnClickListener(this)
        btnReadRawFile.setOnClickListener(this)
        btnSp.setOnClickListener(this)
        btnGetSpData.setOnClickListener(this)
        btnCreateDB.setOnClickListener(this)
        btnAddBook.setOnClickListener(this)
        btnQueryBook.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnSave2File -> saveString()
            R.id.btnReadFile -> readString()
            R.id.btnReadAssetsFile -> readAssetsString()
            R.id.btnReadRawFile -> readRawFile(name = "demo")
            R.id.btnSp -> saveString2Sp()
            R.id.btnGetSpData -> getStringFromSp()
            R.id.btnCreateDB -> createDB()
            R.id.btnAddBook -> addBook()
            R.id.btnQueryBook -> query()
        }
    }

    private fun saveString() {
        val str = etInput.text.toString()
        if (str.isEmpty()) {
            etInput.error = "不能为空"
        } else {
            val res = FileUtils.writeStr2File(this, str, "file.txt")
            ToastUtil.toast(this, if (res) "保存成功" else "保存失败")
        }
    }

    private fun readString() {
        val str = FileUtils.readFile2String(this, "file.txt")
        etInput.setText(str)
        etInput.setSelection(str.length)
    }

    private fun readAssetsString() {
        val str = FileUtils.readAssets2String(assets, "demo.txt")
        etInput.setText(str)
        etInput.setSelection(str.length)
    }

    private fun readRawFile(name: String? = null, id: Int? = null, isId: Boolean = false) {
        val str = isId.let {
            if (it) {
                if (id != null) FileUtils.readRaw2String(this, id) else ""
            } else {
                if (name != null) FileUtils.readRaw2String(this, name) else ""
            }
        }
        etInput.setText(str)
        etInput.setSelection(str.length)
    }

    private fun saveString2Sp() {
        SharedPreferencesUtils.put("name", "谷超")
    }

    private fun getStringFromSp() {
        val str = SharedPreferencesUtils.getString("name", null)
        val str1 = SharedPreferencesUtils.getString("sex", null)
        val str2 = SharedPreferencesUtils.getNotString("age", 0)
        tvShow.text = "name = $str \n" +
                "sex = $str1 \n" +
                "age = $str2"
    }

    private fun createDB() {
        val dbHelper = MyDatabaseHelper(this)
        sqLiteDatabase = dbHelper.writableDatabase
    }

    private fun addBook() {
        if (::sqLiteDatabase.isInitialized) {
            val value1 = ContentValues().apply {
                put("name", "第一行代码")
                put("author", "郭霖")
                put("pages", 692)
                put("price", 99.00)
            }
            val value2 = contentValuesOf(
                "name" to "西游记",
                "author" to "吴承恩",
                "pages" to "565",
                "price" to "48.5"
            )
            sqLiteDatabase.insert(MyDatabaseHelper.TAB_BOOK, null, value1)
            sqLiteDatabase.insert(MyDatabaseHelper.TAB_BOOK, null, value2)

        } else {
            ToastUtil.toast(this, "数据库尚未初始化")
        }
    }

    private fun update() {
        if (::sqLiteDatabase.isInitialized) {
            val value1 = ContentValues().apply {
                put("price", 98.00)
            }
            sqLiteDatabase.update(MyDatabaseHelper.TAB_BOOK, value1, "id = ?", arrayOf("1"))

        } else {
            ToastUtil.toast(this, "数据库尚未初始化")
        }
    }

    private fun delete() {
        if (::sqLiteDatabase.isInitialized) {
            sqLiteDatabase.delete(MyDatabaseHelper.TAB_BOOK, "id = ?", arrayOf("1"))
        } else {
            ToastUtil.toast(this, "数据库尚未初始化")
        }
    }

    private fun query() {
        if (::sqLiteDatabase.isInitialized) {
            val cursor =
                sqLiteDatabase.query(MyDatabaseHelper.TAB_BOOK, null, null, null, null, null, null)
            if (cursor.moveToFirst()) {
                do {
                    val id = cursor.getLong(cursor.getColumnIndex("id"))
                    val name = cursor.getString(cursor.getColumnIndex("name"))
                    val author = cursor.getString(cursor.getColumnIndex("author"))
                    val price = cursor.getFloat(cursor.getColumnIndex("price"))
                    val pages = cursor.getInt(cursor.getColumnIndex("pages"))
                    val book = Book(id, name, author, pages, price)
                    LogG.loge(msg = book.toString())
                    tvShow.text = book.toString()
                } while (cursor.moveToNext())
            }
            cursor.close()
        } else {
            ToastUtil.toast(this, "数据库尚未初始化")
        }
    }

}
