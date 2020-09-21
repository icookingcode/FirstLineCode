package com.guc.firstlinecode.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by guc on 2020/4/29.
 * 描述：消息类
 *
 */
class Msg(var msg: String, var type: Int) : Parcelable {
    companion object {
        const val TYPE_SENT = 1
        const val TYPE_RECEIVED = 2
        val CREATOR = object : Parcelable.Creator<Msg> {
            override fun createFromParcel(parcel: Parcel): Msg {
                return Msg(
                    parcel.readString() ?: "",
                    parcel.readInt()
                )
            }

            override fun newArray(size: Int): Array<Msg?> {
                return arrayOfNulls(size)
            }
        }
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(msg)
        dest.writeInt(type)
    }

    override fun describeContents(): Int {
        return 0
    }

}