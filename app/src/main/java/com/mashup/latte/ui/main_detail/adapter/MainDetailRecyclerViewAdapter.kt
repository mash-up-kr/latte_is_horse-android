package com.mashup.latte.ui.main_detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mashup.latte.R
import com.mashup.latte.ui.main_detail.data.MainDetailImages
import com.mashup.latte.ui.main_detail.viewholder.MainDetailViewHolder

class MainDetailRecyclerViewAdapter : RecyclerView.Adapter<MainDetailViewHolder>() {
    private val images: MutableList<MainDetailImages> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainDetailViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_main_detail_image, parent, false)
        return MainDetailViewHolder(view)
    }

    fun addImages(image: MainDetailImages) {
        images.add(image)
        notifyDataSetChanged()
    }

    fun deleteImages(position: Int) {
        images.removeAt(position)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: MainDetailViewHolder, position: Int) {
        holder.bind(images[position])
        holder.diaryImg.setOnClickListener {
            //이미지 클릭 시 뷰페이져
        }
    }
}

