package com.example.androidpractice2020.database

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.androidpractice2020.notes.Note
import kotlinx.coroutines.flow.Flow

interface NoteDao {
    @Query("SELECT * FROM note")
    fun getListNotes(): Flow<List<Note>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Query("DELETE FROM note")
    suspend fun deleteAll()

    @Update()
    suspend fun update()
}