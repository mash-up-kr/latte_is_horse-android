package com.mashup.latte.ui.calendar

import androidx.annotation.ColorRes
import com.mashup.latte.R

/**
 * Created by Arim on 2020.03.24.
 */
data class CalendarRow(
    val text: String = "",
    val fullText: String = "",
    @ColorRes val textColor: Int = R.color.black,
    val isSelectable: Boolean = true,
    val dot: Boolean = false,
    val drunkDot: Int = R.drawable.img_drunken_guide_dot_level1
)