package com.mashup.latte.ui.main_detail.viewholder

import android.view.View
import android.widget.*
import coil.api.load
import com.mashup.latte.R
import com.mashup.latte.ui.base.BaseViewHolder
import com.mashup.latte.ui.main_detail.data.MainDetailImages

class MainDetailViewHolder(view: View) : BaseViewHolder<MainDetailImages>(view) {
    val diaryImg: ImageView = view.findViewById(R.id.imgMainDetailPhoto)

    //private val alcoholImg: ImageView = view.findViewById(R.id.imgItemAlcohol)

    override fun bind(data: MainDetailImages) {
        with(data) {
            diaryImg.load(imageResource)
            //alcoholImg.load(imageResource)
        }
    }
}