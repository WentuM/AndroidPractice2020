package com.example.androidpractice2020.fragments

import com.example.androidpractice2020.recyclerview.Note

object NoteRepository {
    var arraylist = arrayListOf(
        Note("Danil", "android", 1),
        Note("Anvar", "android", 2),
        Note("Shamil", "android",3),
        Note("Roma", "android", 4)
    )

    fun find(id: Int): Note {
        return this.arraylist[id]
    }

    fun repeat(): ArrayList<Note> {
        val result = ArrayList<Note>()
        for (note: Note in arraylist) {
            result.add(note.rep())
        }
        return result
    }

    fun findById(id: Int): Int {
        var index = 0
        for (note: Note in arraylist) {
            if (note.id == id) return index
            index++
        }
        return -1
    }

    fun makeId(): Int {
        var moreId = 0
        for(note: Note in arraylist)
            if (note.id > moreId) moreId = note.id

        return ++moreId
    }
}