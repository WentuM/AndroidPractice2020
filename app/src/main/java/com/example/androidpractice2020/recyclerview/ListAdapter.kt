package com.example.androidpractice2020.recyclerview

import DiffUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidpractice2020.R
import com.example.androidpractice2020.fragments.NoteRepository

class ListAdapter(
    private var list: ArrayList<Note>,
    private var removeClick: (Int) -> Unit
) : RecyclerView.Adapter<ListHolder>(), TouchInterface {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder = ListHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.note_item, parent, false), removeClick
    )

    override fun onBindViewHolder(holder: ListHolder, position: Int) = holder.bind(list[position])

    override fun getItemCount(): Int = list.size

    fun updateDataSource(newList: ArrayList<Note>) {
        if (newList != list) {
            val callback = DiffUtils(list, newList)
            val result = DiffUtil.calculateDiff(callback, true)
            result.dispatchUpdatesTo(this)

            list = newList
            NoteRepository.arraylist = newList
        }
    }

    override fun onItemDismiss(position: Int) {
        val count = NoteRepository.repeat()
        count.removeAt(position)
        updateDataSource(count)
    }
}