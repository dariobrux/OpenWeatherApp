package com.dariobrux.openweatherapp.common

import java.text.SimpleDateFormat
import java.util.*

/**
 *
 * Created by Dario Bruzzese on 1/12/2020.
 *
 * This class provides all Date methods helpful for the application.
 *
 */

object DateManager {

    /**
     * Convert a string to a Date in format yyyy-MM-dd hh:mm:ss.
     * @param date the date in string format.
     */
    fun toDate(date: String): Date? {
        return kotlin.runCatching {
            SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault())
        }.mapCatching {
            it.parse(date)
        }.getOrNull()
    }
}