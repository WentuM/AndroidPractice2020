package com.example.androidpractice2020.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidpractice2020.R


class ExampleAdapter(exampleList: ArrayList<Note>) :
    RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>() {
    private val mExampleList: ArrayList<Note> = exampleList
    private var mListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(position: Int)
        fun onDeleteClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        mListener = listener
    }

    class ExampleViewHolder(
        itemView: View,
        listener: OnItemClickListener?
    ) :
        RecyclerView.ViewHolder(itemView) {
        var mTextView1: TextView = itemView.findViewById(R.id.title)
        var mTextView2: TextView = itemView.findViewById(R.id.description)
        var mDeleteImage: ImageView = itemView.findViewById(R.id.delete)

        init {
            itemView.setOnClickListener {
                if (listener != null) {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position)
                    }
                }
            }
            mDeleteImage.setOnClickListener {
                if (listener != null) {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onDeleteClick(position)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExampleViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return ExampleViewHolder(v, mListener)
    }

    override fun onBindViewHolder(
        holder: ExampleViewHolder,
        position: Int
    ) {
        val currentItem: Note = mExampleList[position]
        holder.mTextView1.text = currentItem.title
        holder.mTextView2.text = currentItem.description
    }

    override fun getItemCount(): Int {
        return mExampleList.size
    }

}