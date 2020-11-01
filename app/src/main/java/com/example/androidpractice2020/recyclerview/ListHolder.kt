package com.example.androidpractice2020.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidpractice2020.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.note_item.*

class ListHolder(
    override val containerView: View,
    var itemClick: (Int) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    fun bind(note: Note) {
        title.text = note.title
        description.text = note.description
    }

    companion object {
        fun create(parent: ViewGroup, itemClick: (Int) -> Unit): ListHolder =
            ListHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false),
                itemClick
            )
    }
}