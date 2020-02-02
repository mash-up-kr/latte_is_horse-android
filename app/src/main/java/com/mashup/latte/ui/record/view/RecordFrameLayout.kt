package com.mashup.latte.ui.record.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

/**
 * Created by Namget on 2020.01.22.
 */
class RecordFrameLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(measuredWidth, measuredWidth)
    }
}