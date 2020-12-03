package com.dariobrux.openweatherapp.data.local.converter

import androidx.room.TypeConverter
import com.dariobrux.openweatherapp.data.local.model.WeatherInfoEntity
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type

/**
 *
 * Created by Dario Bruzzese on 3/12/2020.
 *
 * This class is the de/serializer of the list of [WeatherInfoEntity]. It's used by Database to convert
 * object <-> string.
 *
 * It uses Moshi.
 *
 */
class WeatherInfoConverter {

    private val moshi = Moshi.Builder().build()
    private val type: Type = Types.newParameterizedType(MutableList::class.java, WeatherInfoEntity::class.java)

    /**
     * Convert the list to json.
     * @return the representation of the list in json.
     */
    @TypeConverter
    fun toJson(list: List<WeatherInfoEntity>): String {
        val adapter = moshi.adapter<List<WeatherInfoEntity>>(type)
        return adapter.toJson(list)
    }

    /**
     * Convert the json to list of objects.
     * @return the object's list
     */
    @TypeConverter
    fun fromJson(str: String): List<WeatherInfoEntity> {
        val adapter = moshi.adapter<List<WeatherInfoEntity>>(type)
        return adapter.fromJson(str) ?: listOf()
    }
}