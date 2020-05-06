package com.guc.firstlinecode.utils

import android.content.Context
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
}