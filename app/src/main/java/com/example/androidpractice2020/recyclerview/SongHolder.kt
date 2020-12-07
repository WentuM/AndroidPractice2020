package com.example.androidpractice2020.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidpractice2020.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.song_item.*

class SongHolder(override val containerView: View, var itemClick: (Int) -> Unit) :
    RecyclerView.ViewHolder(containerView), LayoutContainer {
    fun bind(song: Song) {
        title.text = song.title
        author.text = song.author
        imageView.setImageResource(song.cover)
        itemView.setOnClickListener {
            itemClick(song.id)
        }
    }

    companion object {
        fun create(parent: ViewGroup, itemClick: (Int) -> Unit): SongHolder =
            SongHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.song_item,
                    parent,
                    false
                ),
                itemClick
            )
    }
}
