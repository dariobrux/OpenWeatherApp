package com.dariobrux.openweatherapp.data.remote.model

import com.squareup.moshi.Json

/**
 *
 * Created by Dario Bruzzese on 1/12/2020.
 *
 * This is the data model with the info about the part of day.
 * Some parameters have a different name respects to the original json and
 * they're bind by Moshi.
 *
 */
data class SysData(

    /**
     * Part of the day (n - night, d - day).
     */
    @field:Json(name = "pod")
    var partOfDay: String?

)
