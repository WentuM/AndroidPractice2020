package com.example.androidpractice2020.cardview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidpractice2020.R

class ModelAdapter(
    var modelList: ArrayList<Model>
) : RecyclerView.Adapter<ModelHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelHolder =
        ModelHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        )

    override fun getItemCount(): Int = modelList.size

    override fun onBindViewHolder(holder: ModelHolder, position: Int) =
        holder.bind(modelList[position])

}