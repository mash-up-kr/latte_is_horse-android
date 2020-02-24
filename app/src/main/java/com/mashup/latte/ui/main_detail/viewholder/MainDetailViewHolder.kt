package com.mashup.latte.ui.main_detail.viewholder

import android.view.View
import android.widget.*
import coil.api.load
import com.mashup.latte.R
import com.mashup.latte.ui.base.BaseViewHolder
import com.mashup.latte.ui.main_detail.adapter.MainDetailRecyclerViewAdapter
import com.mashup.latte.ui.main_detail.data.MainDetailImages

class MainDetailViewHolder(
    view: View,
    onImageClickListener: MainDetailRecyclerViewAdapter.OnImageClickListener
) : BaseViewHolder<MainDetailImages>(view), View.OnClickListener {
    private val diaryImg: ImageView = view.findViewById(R.id.imgMainDetailPhoto)
    private val onImageClickListener: MainDetailRecyclerViewAdapter.OnImageClickListener =
        onImageClickListener

    init {
        view.setOnClickListener(this)
    }

    override fun bind(data: MainDetailImages) {
        with(data) {
            diaryImg.load(imageResource)
        }
    }

    override fun onClick(v: View?) {
        onImageClickListener.onImageClick(adapterPosition)
    }
}
