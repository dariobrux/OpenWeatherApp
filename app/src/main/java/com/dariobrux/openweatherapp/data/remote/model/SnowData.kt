package com.dariobrux.openweatherapp.data.remote.model

import com.squareup.moshi.Json

/**
 *
 * Created by Dario Bruzzese on 1/12/2020.
 *
 * This is the data model with the info about the snow.
 * Some parameters have a different name respects to the original json and
 * they're bind by Moshi.
 *
 */
data class SnowData(

    /**
     * Snow volume for last 3 hours.
     */
    @field:Json(name = "3h")
    var volume : Double?,

)
