package com.mashup.latte.data

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

data class DiariesResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Diary>
): Serializable