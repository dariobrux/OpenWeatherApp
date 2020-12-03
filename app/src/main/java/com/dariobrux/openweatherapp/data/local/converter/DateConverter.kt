package com.dariobrux.openweatherapp.data.local.converter

import androidx.room.TypeConverter
import com.dariobrux.openweatherapp.common.DateManager
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*

/**
 *
 * Created by Dario Bruzzese on 3/12/2020.
 *
 * This class is the de/serializer of the Date. It's used by Database to convert object <-> string.
 *
 * It uses Moshi.
 *
 */
class DateConverter {

    private val moshi = Moshi.Builder().build()
    private val type: Type = Types.newParameterizedType(Date::class.java)

    /**
     * Convert the list to json.
     * @return the representation of the list in json.
     */
    @TypeConverter
    fun toJson(date: Date): String {
        val format = SimpleDateFormat(DateManager.DateFormat.YYYY_MM_DD_HH_MM_SS.value, Locale.getDefault())
        return kotlin.runCatching {
            format.format(date)
        }.getOrNull() ?: ""
    }

    /**
     * Convert the json to list of objects.
     * @return the object's list
     */
    @TypeConverter
    fun fromJson(str: String): Date {
        val format = SimpleDateFormat(DateManager.DateFormat.YYYY_MM_DD_HH_MM_SS.value, Locale.getDefault())
        return kotlin.runCatching {
            format.parse(str)
        }.getOrNull() ?: Date()
    }
}