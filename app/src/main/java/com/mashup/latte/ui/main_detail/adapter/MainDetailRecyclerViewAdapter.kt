package com.mashup.latte.ui.main_detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mashup.latte.R
import com.mashup.latte.ui.main_detail.data.MainDetailImages
import com.mashup.latte.ui.main_detail.viewholder.MainDetailViewHolder

class MainDetailRecyclerViewAdapter(onImageClickListener: OnImageClickListener) :
    RecyclerView.Adapter<MainDetailViewHolder>() {
    private val images: MutableList<String> = arrayListOf()
    private val mOnImageClickListener: OnImageClickListener = onImageClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainDetailViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_main_detail_image, parent, false)
        return MainDetailViewHolder(view, mOnImageClickListener)
    }

    fun addImages(image: List<String>) {
        images.addAll(image)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: MainDetailViewHolder, position: Int) {
        holder.bind(images[position])
    }

    interface OnImageClickListener {
        fun onImageClick(position: Int)
    }
}

