package com.dariobrux.openweatherapp.data.model

import com.squareup.moshi.Json

/**
 *
 * Created by Dario Bruzzese on 1/12/2020.
 *
 * This is the data model with the info about the wind.
 * Some parameters have a different name respects to the original json and
 * they're bind by Moshi.
 *
 */
data class WindData(

    /**
     * Wind speed. Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour.
     */
    var speed : Double?,

    /**
     * Wind direction, degrees (meteorological).
     */
    @field:Json(name = "deg")
    var degrees : Int?
)
