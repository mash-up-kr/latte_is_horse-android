package com.mashup.latte.ui.record.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Namget on 2020.01.22.
 */
class TakePictureViewHolder(view: View, selectedCallback: () -> Unit) :
    RecyclerView.ViewHolder(view) {
    
    init {
        view.setOnClickListener {
            selectedCallback()
        }
    }

}