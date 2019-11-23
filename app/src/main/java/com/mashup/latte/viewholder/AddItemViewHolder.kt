package com.mashup.latte.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mashup.latte.dataclass.AddListItem
import kotlinx.android.synthetic.main.add_item.view.*

class AddItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(data: AddListItem) {
        //itemView.item_image.imgUrl = data.imageUrl
        itemView.add_item_text.text = data.name
        itemView.add_item_caffeine_text.text = data.caffeine
        itemView.checkBox.isChecked = data.checked
    }
}