package com.dariobrux.openweatherapp.data.remote.model

/**
 *
 * Created by Dario Bruzzese on 1/12/2020.
 *
 * This is the data model with the info about the city.
 *
 */
data class CityData(

    /**
     * City ID.
     */
    var id : Long?,

    /**
     * City name.
     */
    var name : String?,

    var coord: CoordData?,

    /**
     * Country code (GB, JP etc.).
     */
    var country : String?,

    var population: Long?,

    /**
     * Shift in seconds from UTC.
     */
    var timezone : Long?,

    var sunrise : Long?,

    var sunset: Long?
)
