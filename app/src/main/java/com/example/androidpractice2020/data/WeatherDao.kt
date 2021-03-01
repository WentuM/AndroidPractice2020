package com.example.androidpractice2020.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WeatherDao {


    suspend fun insert(list: List<City>) {
        insrt(*list.toTypedArray())
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insrt(vararg list: City)

    @Query("SELECT * FROM city")
    suspend fun getAllCities(): List<City>

    @Query("SELECT * FROM city WHERE id = :idCity")
    suspend fun getCityById(idCity: Int): City

    @Query("SELECT * FROM city WHERE name = :nameCity")
    suspend fun getCityByName(nameCity: String): City

    @Query("DELETE FROM city")
    suspend fun deleteAllCity()

}
