package com.mashup.latte.dataclass

data class AddListItem (
    //val imageUrl: String,
    val name: String,
    val caffeine: String,
    var checked: Boolean = false
)