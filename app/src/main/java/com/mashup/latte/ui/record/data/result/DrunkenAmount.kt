package com.mashup.latte.ui.record.data.result

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Namget on 2020.02.19.
 */
class DrunkenAmount(
    val name: String,
    val type: String,
    val cup: String,
    val bottle: String
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(name)
        writeString(type)
        writeString(cup)
        writeString(bottle)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<DrunkenAmount> =
            object : Parcelable.Creator<DrunkenAmount> {
                override fun createFromParcel(source: Parcel): DrunkenAmount = DrunkenAmount(source)
                override fun newArray(size: Int): Array<DrunkenAmount?> = arrayOfNulls(size)
            }
    }
}