package com.example.androidpractice2020

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
class FilmAdapter(
    private var list: List<Film>,
    private var itemClick: (Int) -> Unit
) : RecyclerView.Adapter<FilmHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmHolder = FilmHolder.create(parent, itemClick)

    override fun onBindViewHolder(holder: FilmHolder, position: Int) = holder.bind(list[position])

    override fun getItemCount(): Int = list.size
}