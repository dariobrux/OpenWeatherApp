package com.dariobrux.openweatherapp.data.model

import com.squareup.moshi.Json

/**
 *
 * Created by Dario Bruzzese on 1/12/2020.
 *
 * This is the aggregate data model with higher level info about the weather.
 * More specific info about the weather are inside the properties of this data class.
 * Some parameters have a different name respects to the original json and
 * they're bind by Moshi.
 *
 */
data class AggregateData(

    /**
     * Time of data forecasted, unix, UTC.
     */
    @field:Json(name = "dt")
    var dateMillis: Long?,

    var main: MainData?,

    @field:Json(name = "weather")
    var weathers: List<WeatherData>?,

    @field:Json(name = "clouds")
    var cloud: CloudData?,

    var wind: WindData?,

    /**
     * Average visibility, metres.
     */
    var visibility: Int?,

    /**
     * Probability of precipitation
     */
    var pop: Int?,

    var rain: RainData?,

    var snow: SnowData?,

    var sys: SysData?,

    /**
     * Time of data forecasted, ISO, UTC.
     */
    var dateText: String?
)
