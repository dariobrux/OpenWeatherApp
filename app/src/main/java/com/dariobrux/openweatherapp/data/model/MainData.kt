package com.dariobrux.openweatherapp.data.model

import com.squareup.moshi.Json

/**
 *
 * Created by Dario Bruzzese on 1/12/2020.
 *
 * This is the data model with all weather numeric values.
 * Some parameters have a different name respects to the original json and
 * they're bind by Moshi.
 *
 */
data class MainData(

    /**
     * Temperature. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
     */
    var temp: Double?,

    /**
     * This temperature parameter accounts for the human perception of weather.
     * Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
     */
    @field:Json(name = "feels_like")
    var feelsLike: Double?,

    /**
     * Minimum temperature at the moment of calculation.
     * This is minimal forecasted temperature (within large megalopolises and urban areas), use this parameter optionally.
     * Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
     */
    @field:Json(name = "temp_min")
    var tempMin: Double?,

    /**
     * Maximum temperature at the moment of calculation.
     * This is maximal forecasted temperature (within large megalopolises and urban areas), use this parameter optionally.
     * Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
     */
    @field:Json(name = "temp_max")
    var tempMax: Double?,

    /**
     * Atmospheric pressure on the sea level by default, hPa.
     */
    var pressure: Double?,

    /**
     * Atmospheric pressure on the sea level, hPa.
     */
    @field:Json(name = "sea_level")
    var seaLevel: Double?,

    /**
     * Atmospheric pressure on the ground level, hPa.
     */
    @field:Json(name = "grnd_level")
    var groundLevel: Double?,

    /**
     * Humidity, %.
     */
    var humidity: Double?
)
