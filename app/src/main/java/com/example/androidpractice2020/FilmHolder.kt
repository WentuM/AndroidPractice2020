package com.example.androidpractice2020

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.film_item.*

class FilmHolder(
    override val containerView: View,
    var itemClick: (Int) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    fun bind(film: Film) {
        film_name.text = film.name
        film_image.setImageResource(film.imageView)
        itemView.setOnClickListener {
            itemClick(film.id)
        }
    }

    companion object {
        fun create(parent: ViewGroup, itemClick: (Int) -> Unit): FilmHolder =
            FilmHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.film_item, parent, false),
                itemClick
            )
    }
}