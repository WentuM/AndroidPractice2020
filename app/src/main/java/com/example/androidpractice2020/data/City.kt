package com.example.androidpractice2020.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.androidpractice2020.data.api.response.InfoCity
import kotlinx.coroutines.withContext

@Entity(tableName = "city")
data class City(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "temperature")
    var temp: Double,
    @ColumnInfo(name = "description")
    var description: String,
    @ColumnInfo(name = "numb_humidity")
    var numbHumidity: Int,
    @ColumnInfo(name = "deg")
    var deg: Int
) {
    companion object {
        fun mapResponsetoEntity(infoCity: InfoCity): City {
            with(infoCity) {
                return City(
                    id, name, main.temp, weather[0].description, main.humidity, wind.deg
                )
            }
        }
    }
}
