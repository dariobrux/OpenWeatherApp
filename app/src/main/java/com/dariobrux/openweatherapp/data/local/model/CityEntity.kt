package com.dariobrux.openweatherapp.data.local.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
 * Created by Dario Bruzzese on 4/12/2020.
 *
 * This is the city entity stored in the database and shown on screen.
 * I use [cityName] as primary key because I want to override every time the
 * weather stored.
 *
 */
@Entity(tableName = "city")
data class CityEntity(

    @PrimaryKey
    @NonNull
    var cityName: String = "",

    @NonNull
    var sunrise: String = "",

    @NonNull
    var sunset: String = ""
)