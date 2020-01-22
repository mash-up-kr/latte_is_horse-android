package com.mashup.latte.ui.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Namget on 2020.01.22.
 */
abstract class BaseViewHolder<T : Any>(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(data: T)
}