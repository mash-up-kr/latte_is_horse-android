package com.mashup.latte.data.datasource.local.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.mashup.latte.data.datasource.local.DateConverter
import com.mashup.latte.data.datasource.local.ListConverter
import com.mashup.latte.data.dto.request.AlcoholRecord
import com.mashup.latte.data.dto.request.AlcoholRequest
import com.mashup.latte.ui.record.data.result.DrunkenAmount
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Namget on 2020.02.22.
 *
 */
@TypeConverters(DateConverter::class, ListConverter::class)
@Entity(tableName = "alcoholDiary")
data class AlcoholDiary(
    @PrimaryKey
    val id: Long? = null,
    val date: String = "",
    val drunkenStatus: String = "",
    val hanoverStatus: String = "",
    val drunkenAmounts: List<DrunkenAmount> = arrayListOf(),
    val review: String = "",
    val drunkenActionType: String = "",
    val imagePath: Array<String?>? = arrayOfNulls<String>(3),
    val timeStamp: Date = Date()
) : Parcelable {
    fun toAlcoholRequest(): AlcoholRequest {
        return AlcoholRequest(
            review = review,
            date = date,
            drunkenLevel = drunkenStatus,
            hangoverLevel = hanoverStatus,
            actionType = drunkenActionType,
            alcoholRecords = drunkenAmountsToAlcoholRecords(drunkenAmounts),
            actionTypeImg = ""
        )
    }

    private fun drunkenAmountsToAlcoholRecords(drunkenAmounts: List<DrunkenAmount>): List<AlcoholRecord> {
        val result: MutableList<AlcoholRecord> = mutableListOf()
        for (drunkenAmount in drunkenAmounts) {
            result.add(
                AlcoholRecord(
                    bottles = drunkenAmount.bottle.toDouble(),
                    glasses = drunkenAmount.cup.toDouble(),
                    alcoholType = drunkenAmount.type,
                    alcoholName = drunkenAmount.name
                )
            )
        }
        return result
    }

    constructor(source: Parcel) : this(
        source.readValue(Long::class.java.classLoader) as Long?,
        source.readString(),
        source.readString(),
        source.readString(),
        ArrayList<DrunkenAmount>().apply {
            source.readList(
                this,
                DrunkenAmount::class.java.classLoader
            )
        },
        source.readString(),
        source.readString(),
        source.createStringArray(),
        source.readSerializable() as Date
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeValue(id)
        writeString(date)
        writeString(drunkenStatus)
        writeString(hanoverStatus)
        writeList(drunkenAmounts)
        writeString(review)
        writeString(drunkenActionType)
        writeStringArray(imagePath)
        writeSerializable(timeStamp)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<AlcoholDiary> = object : Parcelable.Creator<AlcoholDiary> {
            override fun createFromParcel(source: Parcel): AlcoholDiary = AlcoholDiary(source)
            override fun newArray(size: Int): Array<AlcoholDiary?> = arrayOfNulls(size)
        }
    }
}