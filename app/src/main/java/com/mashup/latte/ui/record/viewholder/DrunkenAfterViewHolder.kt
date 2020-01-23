package com.mashup.latte.ui.record.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import coil.api.load
import com.mashup.latte.R
import com.mashup.latte.ui.base.BaseViewHolder
import com.mashup.latte.ui.record.data.DrunkenAfter

/**
 * Created by Namget on 2020.01.23.
 */
class DrunkenAfterViewHolder(view: View) : BaseViewHolder<DrunkenAfter>(view) {
    val selectBox: ImageView = view.findViewById(R.id.imgDrunkenSelectBox)
    private val drunkenImg: ImageView = view.findViewById(R.id.imgDrunken)
    private val drunkenTitle: TextView = view.findViewById(R.id.txtDrunkenItemTitle)

    override fun bind(data: DrunkenAfter) {
        drunkenImg.load(data.imageRes)
        drunkenTitle.text = data.text
    }
}