package com.guc.firstlinecode.ktexercise

/**
 * Created by guc on 2020/6/3.
 * 描述：DSL 定义特有的语法结构
 */

class Dependency {
    val libraries = ArrayList<String>()
    fun implementation(lib: String) {
        libraries.add(lib)
    }
}

fun dependencies(block: Dependency.() -> Unit): List<String> {
    val dependency = Dependency()
    dependency.block()
    return dependency.libraries
}

class Td {
    var content = ""
    fun html() = "\n\t\t<td>$content</td>"
}

class Tr {
    private val children = ArrayList<Td>()
    fun td(block: Td.() -> String) {
        val td = Td()
        td.content = td.block()
        children.add(td)
    }

    fun html(): String {
        val sb = StringBuilder()
        sb.append("\n\t<tr>")
        for (child in children) {
            sb.append(child.html())
        }
        sb.append("\n\t</tr>")
        return sb.toString()
    }
}

class Table {
    private val children = ArrayList<Tr>()
    fun tr(block: Tr.() -> Unit) {
        val tr = Tr()
        tr.block()
        children.add(tr)
    }

    fun html(): String {
        val sb = StringBuilder()
        sb.append("\n<table>")
        for (child in children) {
            sb.append(child.html())
        }
        sb.append("\n</table>")
        return sb.toString()
    }
}

fun table(block: Table.() -> Unit): String {
    val table = Table()
    table.block()
    return table.html()
}

fun main(args: Array<String>) {
    val libraries = dependencies {
        implementation("com.squareup.retrofit2:retrofit:2.6.1")
        implementation("com.squareup.retrofit2:converter-gson:2.6.1")
    }

    for (lib in libraries) {
        println(lib)
    }

    val html = table {
        tr {
            td { "Apple" }
            td { "Orange" }
            td { "Grape" }
        }
        tr {
            td { "Pear" }
            td { "Banana" }
            td { "Watermelon" }
        }
    }
    println(html)

}
