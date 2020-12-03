package com.dariobrux.openweatherapp.data.local.model

/**
 *
 * Created by Dario Bruzzese on 1/12/2020.
 *
 * This is the weather entity stored in the database and shown on screen.
 *
 */
data class WeatherEntity(

    var cityName: String,

    var weatherInfo: WeatherInfoEntity
)
