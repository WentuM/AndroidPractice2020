package com.example.androidpractice2020.recyclerview

import com.example.androidpractice2020.R

object SongRepository {
    val songList: ArrayList<Song> = arrayListOf(
        Song(
            "Monday",
            R.drawable.rocket,
            R.raw.monday,
            "Rocket",
            0
        ),
        Song(
            "Death Bed",
            R.drawable.death_bed,
            R.raw.death_bed,
            "Powfu",
            1
        ),
        Song(
            "Deep End",
            R.drawable.deep_end,
            R.raw.deep_end,
            "Foushee",
            2
        ),
        Song(
            "Money Long",
            R.drawable.money_long,
            R.raw.money_long,
            "Kizaru",
            3
        ),
        Song(
            "Дежавю",
            R.drawable.money_long,
            R.raw.dezhavu,
            "Kizaru",
            4
        ),
        Song(
            "Now That You're Gone",
            R.drawable.now_that_gone,
            R.raw.gone,
            "Daniel Silver",
            5
        )
    )

    fun find(id: Int): Song {
        return songList[id]
    }
}