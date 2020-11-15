package com.example.androidpractice2020.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SongAdapter(var list: ArrayList<Song>, var itemClick: (Int) -> Unit) :
    RecyclerView.Adapter<SongHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            SongHolder =
        SongHolder.create(
            parent,
            itemClick
        )

    override fun onBindViewHolder(holder: SongHolder, position: Int) = holder.bind(list[position])

    override fun getItemCount(): Int = list.size
}