package com.mashup.latte.ui.record.data.result

/**
 * Created by Namget on 2020.02.19.
 */
data class DetailResult(
    var date: String = "",
    var drunkenStatus: String = "",
    var hanoverStatus: String = "",
    var drunkenAmounts: List<DrunkenAmount> = arrayListOf()
)