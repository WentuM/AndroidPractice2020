package com.example.androidpractice2020.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidpractice2020.R
import com.example.androidpractice2020.fragments.NoteRepository
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.note_item.*

class ListHolder(
    override val containerView: View,
    private val removeClick: (Int) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    var remove = itemView.findViewById<ImageView>(R.id.delete)

    fun bind(note: Note) {
        title.text = note.title
        description.text = note.description

        remove.setOnClickListener {
            removeClick(NoteRepository.findById(note.id))
        }
    }
}