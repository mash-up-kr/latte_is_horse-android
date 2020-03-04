package com.mashup.latte.ui.record.data

import android.text.BoringLayout
import android.view.View
import androidx.annotation.ColorRes
import com.mashup.latte.R

/**
 * Created by Namget on 2020.03.02.
 */
data class CalendarRow(
    val text: String = "",
    @ColorRes val textColor: Int = R.color.black,
    val isSelectable : Boolean = true,
    val isSelected : Boolean = false
)