package com.dariobrux.openweatherapp.data.local.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

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
    var date: String = "",

    @NonNull
    var temp: Double = 0.0,

    @NonNull
    var tempMin: Double = 0.0,

    @NonNull
    var tempMax: Double = 0.0,

    @NonNull
    var title: String = "",

    @NonNull
    var subtitle: String = "",

    @NonNull
    var icon: String = ""

) : Serializable