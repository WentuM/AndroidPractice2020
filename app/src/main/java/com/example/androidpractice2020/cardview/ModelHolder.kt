package com.example.androidpractice2020.cardview

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.androidpractice2020.R
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ModelHolder(
    containerView: View
) : RecyclerView.ViewHolder(containerView) {

    var photo = itemView.findViewById<ShapeableImageView>(R.id.photo)
    var name = itemView.findViewById<TextView>(R.id.name)
    var mainPhoto = itemView.findViewById<ViewPager2>(R.id.main_photo)
    var name2 = itemView.findViewById<TextView>(R.id.name2)
    var description = itemView.findViewById<TextView>(R.id.descriptionView)
    var tab = itemView.findViewById<TabLayout>(R.id.tab_layout)

    fun bind(model: Model) {
        Log.i("about", "Card bind")
        photo.setImageResource(model.image)
        name.text = model.title
        description.text = model.desc
        name2.text = model.title
        mainPhoto.adapter = model.arrayPage

//        tab.setupWithViewPager(mainPhoto, true) ViewPager1
        TabLayoutMediator(tab, mainPhoto) { tab, postition -> }.attach() //ViewPager2
    }

}
