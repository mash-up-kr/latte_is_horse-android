package com.mashup.latte.ui.record.viewholder

import android.net.Uri
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import coil.api.load
import com.mashup.latte.R
import com.mashup.latte.ui.base.BaseViewHolder
import com.mashup.latte.ui.record.data.DrunkenAfter

/**
 * Created by Namget on 2020.01.23.
 */
class DrunkenAfterViewHolder(val view: View, callback: (Int) -> Unit) :
    BaseViewHolder<DrunkenAfter>(view) {
    private val drunkenImg: ImageView = view.findViewById(R.id.imgDrunken)

    //frameLayout에서는 api 1부터 존재 error
    init {
        view.setOnClickListener {
            callback(adapterPosition)
        }
    }


    override fun bind(data: DrunkenAfter) {
        drunkenImg.load(data.imageRes)
        if (data.isChecked) {
            drunkenImg.isSelected = true
            view.foreground = null
        } else {
            drunkenImg.isSelected = false
            view.foreground =
                drunkenImg.context.applicationContext.getDrawable(R.drawable.shape_rectangle_white_transparent)
        }
    }
}