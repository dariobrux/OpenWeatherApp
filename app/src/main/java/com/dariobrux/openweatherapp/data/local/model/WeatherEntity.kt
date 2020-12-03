package com.dariobrux.openweatherapp.data.local.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
 * Created by Dario Bruzzese on 1/12/2020.
 *
 * This is the weather entity stored in the database and shown on screen.
 * I use [cityName] as primary key because I want to override every time the
 * weather stored.
 *
 */
@Entity(tableName = "weather")
data class WeatherEntity(

    @PrimaryKey
    @NonNull
    var cityName: String,

    var weatherInfoList: List<WeatherInfoEntity>
)
