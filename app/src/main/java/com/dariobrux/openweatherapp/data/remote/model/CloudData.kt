package com.dariobrux.openweatherapp.data.remote.model

import com.squareup.moshi.Json

/**
 *
 * Created by Dario Bruzzese on 1/12/2020.
 *
 * This is the cloud data model with the info about the clouds.
 * Some parameters have a different name respects to the original json and
 * they're bind by Moshi.
 *
 */
data class CloudData(

    /**
     * Cloudiness, %.
     */
    @field:Json(name = "all")
    var percentage : Int?
)
