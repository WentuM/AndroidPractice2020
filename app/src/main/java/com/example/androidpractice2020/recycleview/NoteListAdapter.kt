package com.example.androidpractice2020.recycleview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidpractice2020.R
import com.example.androidpractice2020.interfacecell.CellClickListener
import com.example.androidpractice2020.notes.Note

class NoteListAdapter(private val cellClickListener: CellClickListener) : androidx.recyclerview.widget.ListAdapter<Note, NoteListAdapter.NoteViewHolder>(
    NOTE_COMPARATOR
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder.create(
            parent
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)
        holder.bind(note.title, note.description)

        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener()
        }
        holder.noteImageDelete.setOnClickListener {
            cellClickListener.onDeleteClickListener()
        }
    }

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val noteTitle: TextView = itemView.findViewById(R.id.note_title)
        private val noteDescription: TextView = itemView.findViewById(R.id.note_description)
        val noteImageDelete: ImageView = itemView.findViewById(R.id.delete)

        fun bind(title: String?, description: String?) {
            noteTitle.text = title
            noteDescription.text = description
        }

        companion object {
            fun create(parent: ViewGroup): NoteViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_note_list, parent, false)
                return NoteViewHolder(
                    view
                )
            }
        }
    }

    companion object {
        private val NOTE_COMPARATOR = object : DiffUtil.ItemCallback<Note>() {
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem.title == newItem.title
            }
        }
    }
}