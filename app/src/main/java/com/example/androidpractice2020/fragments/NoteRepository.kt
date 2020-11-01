package com.example.androidpractice2020.fragments

import com.example.androidpractice2020.recyclerview.Note

class NoteRepository {
    var arraylist = arrayListOf(
        Note("Danil", "android", 1),
        Note("Anvar", "android", 2),
        Note("Shamil", "android", 3),
        Note("Roma", "android", 4)
    )

    fun find(id: Int): Note {
        return this.arraylist[id]
    }
}