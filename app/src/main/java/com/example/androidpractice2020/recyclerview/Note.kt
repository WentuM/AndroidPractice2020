package com.example.androidpractice2020.recyclerview


class Note(
    val title: String, val description: String, val id: Int
) {
    fun rep() : Note = Note(this.title, this.description, this.id)
}

