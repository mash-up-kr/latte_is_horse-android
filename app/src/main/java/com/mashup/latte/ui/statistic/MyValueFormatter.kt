package com.mashup.latte.ui.statistic

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter

class MyValueFormatter : ValueFormatter {
    private var xAxisLabel: ArrayList<String>

    constructor(label: ArrayList<String>) : super() {
        xAxisLabel = label
    }

    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return xAxisLabel.getOrNull(value.toInt()) ?: value.toString()
    }

    override fun getFormattedValue(value: Float): String {
        return value.toInt().toString() + " 잔"
    }
}