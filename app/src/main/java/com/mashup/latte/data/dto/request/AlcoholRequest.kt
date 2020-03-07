package com.mashup.latte.data.dto.request

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Namget on 2020.03.05.
 */
data class AlcoholRequest(
    // 오늘은 밥을 먹엇다. default "-"
    val review: String,
    //2019.01.01
    val date: String,
    //취함정도 : 멀쩡함, 조금취함, 많이취함, 댕댕이
    @SerializedName("drunken_level")
    val drunkenLevel: String,
    //숙취정도 :  상쾌 으윽 죽음
    @SerializedName("hangover_level")
    val hangoverLevel: String,
    //주사 : 음주가무, 살짝 알딸딸, 완전멀쩡, 무지개토, 눈물줄줄, 스킨쉽귀신, 필름끊김, 꿀잠쿨쿨, 귀가요정
    @SerializedName("action_type")
    val actionType: String,
    @SerializedName("alcohol_records")
    val alcoholRecords: List<AlcoholRecord>,
    @SerializedName("action_type_img")
    val actionTypeImg: String,
    @SerializedName("image_set")
    val imageSet: List<ImageSet>
)

data class AlcoholRecord(
    val bottles: Double,
    val glasses: Double,
    @SerializedName("alcohol_type")
    val alcoholType: String,
    @SerializedName("alcohol_name")
    val alcoholName: String
) : Serializable

data class ImageSet(
    @SerializedName("main_image")
    val mainImage: String
)