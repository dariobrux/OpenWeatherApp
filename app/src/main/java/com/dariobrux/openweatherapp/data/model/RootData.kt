package com.dariobrux.openweatherapp.data.model

import com.squareup.moshi.Json

/**
 *
 * Created by Dario Bruzzese on 1/12/2020.
 *
 * This is the root data model retrieved by Retrofit.
 * Some parameters have a different name respects to the original json and
 * they're bind by Moshi.
 *
 */
data class RootData(

    var code: Int?,

    var message: String?,

    @field:Json(name = "cnt")
    var countItems: Int?,

    @field:Json(name = "list")
    var aggregates: List<AggregateData>?,

    var city: CityData?
)
