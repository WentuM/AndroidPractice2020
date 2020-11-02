package com.example.androidpractice2020.cardview

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.ColorSpace.Model
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.example.androidpractice2020.R


class PagerAdapter(
    val listImages: ArrayList<Int>
) : RecyclerView.Adapter<PagerHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerHolder =
        PagerHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.all_image, parent, false)
        )


    override fun getItemCount(): Int = listImages.size

    override fun onBindViewHolder(holder: PagerHolder, position: Int) = holder.itemView.run {
        holder.bind(listImages[position])
    }
}