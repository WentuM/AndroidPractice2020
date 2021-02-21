package com.example.androidpractice2020.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidpractice2020.factory.InfoCity

class CityAdapter(var list: ArrayList<InfoCity>, var itemClick: (Int) -> Unit): RecyclerView.Adapter<CityHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            CityHolder =
        CityHolder.create(
            parent,
            itemClick
        )

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CityHolder, position: Int) = holder.bind(list[position])
}