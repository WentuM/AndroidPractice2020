package com.example.androidpractice2020.data.database

import android.content.Context
import androidx.room.*
import com.example.androidpractice2020.data.database.dao.WeatherDao
import com.example.androidpractice2020.data.database.entity.City

@Database(entities = arrayOf(City::class), version = 2, exportSchema = false)
abstract class WeatherRoomDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

    companion object {

        @Volatile
        private var INSTANCE: WeatherRoomDatabase? = null

        fun getDataBase(context: Context): WeatherRoomDatabase {
            return INSTANCE
                ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeatherRoomDatabase::class.java,
                    "city_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}