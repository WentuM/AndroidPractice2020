package com.example.androidpractice2020.database

import android.app.Application
import com.example.androidpractice2020.database.NoteRepository
import com.example.androidpractice2020.database.NoteRoomDataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class NotesApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())


    val database by lazy { NoteRoomDataBase.getDatabase(this, applicationScope) }
    val repository by lazy {
        NoteRepository(
            database.noteDao()
        )
    }
}