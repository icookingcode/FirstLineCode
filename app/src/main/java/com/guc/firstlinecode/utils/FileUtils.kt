package com.guc.firstlinecode.utils

import android.content.Context
import android.content.res.AssetManager
import android.net.Uri
import android.os.Build
import androidx.core.content.FileProvider
import com.guc.firstlinecode.R
import java.io.*


/**
 * Created by guc on 2020/5/6.
 * 描述：文件操作工具
 */
object FileUtils {
    /**
     * 保存字符串到文字
     * 文件默认保存到 /data/data/<package name>/files/目录下
     */
    fun writeStr2File(
        context: Context,
        inputText: String,
        fileName: String,
        mode: Int = Context.MODE_PRIVATE
    ): Boolean {
        return try {
            val output = context.openFileOutput(fileName, mode)
            val writer = BufferedWriter(OutputStreamWriter(output))
            writer.use {//kotlin内置的扩展函数，保证lambda表达式中代码执行完毕后自动关闭外层的数据流
                it.write(inputText)
            }
            true
        } catch (e: IOException) {
            e.printStackTrace()
            false
        }
    }

    /**
     * 将信息写入文件
     *
     * @param filePath 文件路径
     * @param content  待保存内容
     * @param append   是否追加
     * @return true：保存成功  false:保存失败
     */
    fun writeStr2File(
        filePath: String,
        content: String,
        append: Boolean
    ): Boolean {
        val f = File(filePath)
        if (!f.exists()) {
            f.parentFile?.mkdirs()
            f.createNewFile()
        }
        return try {
            val output = FileOutputStream(filePath, append)
            val writer = BufferedWriter(OutputStreamWriter(output))
            writer.use {//kotlin内置的扩展函数，保证lambda表达式中代码执行完毕后自动关闭外层的数据流
                it.write(content)
            }
            true
        } catch (e: IOException) {
            e.printStackTrace()
            false
        }
    }
    /**
     * 读取文件中内容
     */
    fun readFile2String(context: Context, fileName: String): String {
        return try {
            val content = StringBuilder()
            val output = context.openFileInput(fileName)
            val reader = BufferedReader(InputStreamReader(output))
            reader.use {//kotlin内置的扩展函数，保证lambda表达式中代码执行完毕后自动关闭外层的数据流
                reader.forEachLine {
                    content.append(it)
                }
            }
            content.toString()
        } catch (e: IOException) {
            e.printStackTrace()
            "IOException"
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            "FileNotFoundException"
        }
    }

    /**
     * 读取Assets下文件
     */
    fun readAssets2String(assetManager: AssetManager, fileName: String): String {
        return try {
            val content = java.lang.StringBuilder()
            val inputStream = assetManager.open(fileName)
            val reader = BufferedReader(InputStreamReader(inputStream))
            reader.use {//kotlin内置的扩展函数，保证lambda表达式中代码执行完毕后自动关闭外层的数据流
                reader.forEachLine {
                    content.append(it)
                }
            }
            content.toString()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            "FileNotFoundException"
        }
    }

    /**
     * 读取Assets下文件
     */
    fun readRaw2String(context: Context, rawResId: Int): String {
        return try {
            val content = java.lang.StringBuilder()
            val inputStream = context.resources.openRawResource(rawResId)
            val reader = BufferedReader(InputStreamReader(inputStream))
            reader.use {//kotlin内置的扩展函数，保证lambda表达式中代码执行完毕后自动关闭外层的数据流
                reader.forEachLine {
                    content.append(it)
                }
            }
            content.toString()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            "FileNotFoundException"
        }
    }

    /**
     * 读取Assets下文件
     */
    fun readRaw2String(context: Context, rawName: String): String {
        return try {
            val content = java.lang.StringBuilder()
            val field = R.raw::class.java.getField(rawName)
            val inputStream = context.resources.openRawResource(field.getInt(null))
            val reader = BufferedReader(InputStreamReader(inputStream))
            reader.use {//kotlin内置的扩展函数，保证lambda表达式中代码执行完毕后自动关闭外层的数据流
                reader.forEachLine {
                    content.append(it)
                }
            }
            content.toString()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            "FileNotFoundException"
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
            "NoSuchFieldException"
        }
    }

    fun getUri(
        context: Context?,
        authority: String?,
        file: File?
    ): Uri? {
        return  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //设置7.0以上共享文件，分享路径定义在xml/file_paths.xml
            FileProvider.getUriForFile(context!!, authority!!, file!!)
        } else {
            // 7.0以下,共享文件
            Uri.fromFile(file)
        }

    }
}