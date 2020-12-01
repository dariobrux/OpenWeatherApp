package com.dariobrux.openweatherapp.data.local.model

import java.util.*

/**
 *
 * Created by Dario Bruzzese on 1/12/2020.
 *
 * This is the weather entity stored in the database and shown on screen.
 *
 */
data class WeatherEntity(

    var date: Date,

    var cityName: String,

    var temp: Double,

    var tempMin: Double,

    var tempMax: Double,

    var title: String,

    var subtitle: String,

    var icon: String
)
