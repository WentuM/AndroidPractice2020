package com.example.androidpractice2020.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ListAdapter(
    private var list: ArrayList<Note>,
    private var itemClick: (Int) -> Unit
) : RecyclerView.Adapter<ListHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Int)
        fun onDeleteClick(position: Int)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder = ListHolder.create(parent, itemClick)

    override fun onBindViewHolder(holder: ListHolder, position: Int) = holder.bind(list[position])

    override fun getItemCount(): Int = list.size

    fun removeItem(viewHolder: ListHolder) {
        var removePosition = viewHolder.adapterPosition
        list.removeAt(removePosition)
        notifyItemRemoved(removePosition)
        notifyItemChanged(removePosition, list.size)
//        var removeItem = list[viewHolder.adapterPosition].toString()
//        list.removeAt(viewHolder.adapterPosition)
    }

}