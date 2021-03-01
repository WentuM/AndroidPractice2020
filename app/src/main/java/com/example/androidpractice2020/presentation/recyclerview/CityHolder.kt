package com.example.androidpractice2020.presentation.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import com.example.androidpractice2020.R
import com.example.androidpractice2020.data.City
import kotlinx.android.synthetic.main.city_item.*

class CityHolder(override val containerView: View, var itemClick: (Int) -> Unit) :
    RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(city: City) {

        text_name_list.text = city.name
        text_temp_list.text = "${city.temp} \u2103"

        when(city.temp) {
            in -30.0..-18.0 -> text_temp_list.setTextColor(-65536)
            in -18.0..-6.0 -> text_temp_list.setTextColor(-256)
            in -6.0..6.0 -> text_temp_list.setTextColor(-16711681)
            in 6.0..18.0 -> text_temp_list.setTextColor(-16711936)
            in 18.0..30.0 -> text_temp_list.setTextColor(-16777216)
        }

        itemView.setOnClickListener {
            itemClick(city.id)
        }
    }

    companion object {
        fun create(parent: ViewGroup, itemClick: (Int) -> Unit): CityHolder =
            CityHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.city_item,
                    parent,
                    false
                ),
                itemClick
            )
    }

}
