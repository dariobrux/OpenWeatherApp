package com.dariobrux.openweatherapp.data.local.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 *
 * Created by Dario Bruzzese on 3/12/2020.
 *
 * This is the info entity about the weather stored in the database and shown on screen.
 *
 */
@Entity(tableName = "weatherInfo")
data class WeatherInfoEntity(

    @PrimaryKey
    @NonNull
    var date: Date,

    var temp: Double,

    var tempMin: Double,

    var tempMax: Double,

    var title: String,

    var subtitle: String,

    var icon: String
)
