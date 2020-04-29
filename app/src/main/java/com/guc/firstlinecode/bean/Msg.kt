package com.guc.firstlinecode.bean

/**
 * Created by guc on 2020/4/29.
 * 描述：消息类
 *
 */
class Msg(val msg: String, val type: Int) {
    companion object {
        const val TYPE_SENT = 1
        const val TYPE_RECEIVED = 2
    }
}