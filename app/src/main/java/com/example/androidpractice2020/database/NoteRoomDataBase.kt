package com.example.androidpractice2020.database

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.androidpractice2020.notes.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteRoomDataBase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: NoteRoomDataBase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): NoteRoomDataBase {
            return INSTANCE
                ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteRoomDataBase::class.java,
                    "note"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(
                        NoteDatabaseCallback(
                            scope
                        )
                    )
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class NoteDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(
                            database.noteDao()
                        )
                    }
                }
            }
        }

        suspend fun populateDatabase(noteDao: NoteDao) {
            noteDao.deleteAll()

            var example = Note(0, "hqweqweo", "hqweqwe")
            noteDao.insert(example)
            example = Note(1, "qweqweqwe","qweqwqweqwewq00")
            noteDao.insert(example)
        }
    }
}