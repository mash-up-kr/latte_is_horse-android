package com.mashup.latte.ui.record.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import coil.api.load
import com.mashup.latte.R
import com.mashup.latte.ui.base.BaseViewHolder
import com.mashup.latte.ui.record.data.Alcohol
import com.mashup.latte.ui.record.data.DrunkenAfter
import com.mashup.latte.ui.record.data.Soju

/**
 * Created by Namget on 2020.01.23.
 */
class AlcoholViewHolder(view: View) : BaseViewHolder<Alcohol>(view) {
    private val alcoholImg: ImageView = view.findViewById(R.id.imgItemAlcohol)
    private val alcoholTitle: ImageView = view.findViewById(R.id.txtItemAlcohol)
    private val drunkenImg: ImageView = view.findViewById(R.id.imgDrunken)
    private val drunkenTitle: TextView = view.findViewById(R.id.txtDrunkenItemTitle)

    override fun bind(data: Alcohol) {
        //TODO
    }
}