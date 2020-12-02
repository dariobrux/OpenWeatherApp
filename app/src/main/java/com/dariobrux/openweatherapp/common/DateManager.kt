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

    enum class DateFormat(val value: String) {
        YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd hh:mm:ss"),
        MMM_D_YYYY("MMM d, yyyy")
    }

    /**
     * Convert a string to a Date in format yyyy-MM-dd hh:mm:ss.
     * @param date the date in string format.
     * @return the new formatted date.
     */
    fun toDate(date: String): Date? {
        return kotlin.runCatching {
            SimpleDateFormat(DateFormat.YYYY_MM_DD_HH_MM_SS.value, Locale.getDefault())
        }.mapCatching {
            it.parse(date)
        }.getOrNull()
    }
}