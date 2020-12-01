package com.dariobrux.openweatherapp.data.model

/**
 *
 * Created by Dario Bruzzese on 1/12/2020.
 *
 * This is the data model with the info about the coordinates.
 *
 */
data class CoordData(

    /**
     * City geo location, latitude.
     */
    var lat: Double?,

    /**
     * City geo location, longitude.
     */
    var lon: Double?,
)
