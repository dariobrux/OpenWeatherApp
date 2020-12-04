package com.dariobrux.openweatherapp.data.local.converter

import androidx.room.TypeConverter
import com.dariobrux.openweatherapp.data.local.model.CityEntity
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
class CityConverter {

    private val moshi = Moshi.Builder().build()
    private val type: Type = Types.newParameterizedType(CityEntity::class.java)

    /**
     * Convert the item to json.
     * @return the representation of the item in json.
     */
    @TypeConverter
    fun toJson(item: CityEntity): String {
        val adapter = moshi.adapter<CityEntity>(type)
        return adapter.toJson(item)
    }

    /**
     * Convert the json to list of objects.
     * @return the object's list
     */
    @TypeConverter
    fun fromJson(str: String): CityEntity? {
        val adapter = moshi.adapter<CityEntity>(type)
        return adapter.fromJson(str)
    }
}