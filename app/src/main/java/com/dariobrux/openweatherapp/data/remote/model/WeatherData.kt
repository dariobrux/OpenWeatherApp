package com.dariobrux.openweatherapp.data.remote.model

/**
 *
 * Created by Dario Bruzzese on 1/12/2020.
 *
 * This is the weather data model with many info about the weather.
 *
 */
data class WeatherData(

    /**
     * Weather condition id.
     */
    var id : Int?,

    /**
     * Group of weather parameters (Rain, Snow, Extreme etc.).
     */
    var main: String?,

    /**
     * Weather condition within the group. You can get the output in your language.
     */
    var description : String?,

    /**
     * Weather icon id.
     */
    var icon: String?
)
