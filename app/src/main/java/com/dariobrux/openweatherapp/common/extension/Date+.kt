package com.dariobrux.openweatherapp.common.extension

import com.dariobrux.openweatherapp.common.DateManager
import java.util.*

/**
 *
 * Created by Dario Bruzzese on 2/12/2020.
 *
 * This file contains all the Date related extended methods.
 *
 */

/**
 * Convert a date to a particular format.
 * @param format the new format that the date will have.
 * @return the new date in string format.
 */
fun Date.format(format: DateManager.DateFormat): String {
    return android.text.format.DateFormat.format(format.value, this).toString()
}