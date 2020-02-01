package com.mashup.latte.retrofit.model

import java.io.Serializable


data class AlcoholLevel(
    val bottles: Double,
    val glasses: Double,
    val diary: Int
): Serializable

data class Diary(
    val creator: Int,
    val review: String,
    val data: String,
    val drunken_level: String,
    val hangover_level: String,
    val action_type: String,
    val alcohol_records: List<AlcoholLevel>
): Serializable