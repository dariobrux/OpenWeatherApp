package com.dariobrux.openweatherapp.data.model

import com.squareup.moshi.Json

/**
 *
 * Created by Dario Bruzzese on 1/12/2020.
 *
 * This is the data model with the info about the rain.
 * Some parameters have a different name respects to the original json and
 * they're bind by Moshi.
 *
 */
data class RainData(

    /**
     * Rain volume for last 3 hours, mm.
     */
    @field:Json(name = "3h")
    var volume : Double?,

)
