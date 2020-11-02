package com.example.androidpractice2020.cardview

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidpractice2020.R

class PagerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var allImage = itemView.findViewById<ImageView>(R.id.all_image)

    fun bind(image: Int) {
        allImage.setImageResource(image)
    }
}
