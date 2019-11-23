package com.mashup.latte.ui.statistic

import android.graphics.Canvas
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.renderer.XAxisRenderer
import com.github.mikephil.charting.utils.*

class MyXAxisRenderer : XAxisRenderer {

    constructor(
        viewPortHandler: ViewPortHandler,
        xAxis: XAxis,
        transformer: Transformer
    ) : super(
        viewPortHandler,
        xAxis,
        transformer
    )

    override fun drawLabel(
        c: Canvas?,
        formattedLabel: String?,
        x: Float,
        y: Float,
        anchor: MPPointF?,
        angleDegrees: Float
    ) {
        val line = formattedLabel!!.split("\n")
        Utils.drawXAxisValue(c, line[0], x, y, mAxisLabelPaint, anchor, angleDegrees)
        Utils.drawXAxisValue(
            c,
            line[1],
            x,
            y + mAxisLabelPaint.getTextSize(),
            mAxisLabelPaint,
            anchor,
            angleDegrees
        )
    }
}