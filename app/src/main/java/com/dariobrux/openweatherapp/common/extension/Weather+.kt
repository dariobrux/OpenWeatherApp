package com.dariobrux.openweatherapp.common.extension

import com.dariobrux.openweatherapp.common.DateManager
import com.dariobrux.openweatherapp.data.local.model.WeatherEntity
import com.dariobrux.openweatherapp.data.remote.model.AggregateData
import com.dariobrux.openweatherapp.data.remote.model.RootData

/**
 *
 * Created by Dario Bruzzese on 1/12/2020.
 *
 * This file contains all the Weather related extended methods.
 *
 */

/**
 * Convert the [RootData] object to a list of [WeatherEntity].
 * @return the not null and not empty list of [WeatherEntity].
 */
fun RootData?.toWeatherEntityList(): List<WeatherEntity> {

    this ?: return emptyList()
    this.city ?: return emptyList()
    this.city!!.name ?: return emptyList()
    this.city!!.timezone ?: return emptyList()
    if (this.aggregates.isNullOrEmpty()) return emptyList()

    return this.aggregates!!.filter {
        checkDate(it) && checkTemperature(it) && checkWeather(it)
    }.map {
        WeatherEntity(
            date = DateManager.toDate(it.dateText!!)!!,
            cityName = this.city!!.name!!,
            temp = it.main!!.temp!!,
            tempMin = it.main!!.tempMin!!,
            tempMax = it.main!!.tempMax!!,
            title = it.weathers!!.first().main!!,
            subtitle = it.weathers!!.first().description!!,
            icon = it.weathers!!.first().icon ?: "",
        )
    }
}

/**
 * Groups the list by date and flattens the result obtaining a list of [Any].
 */
fun List<WeatherEntity>.toFlattenGroupedDateWeatherList(): List<Any> {
    val map = this.groupBy {
        it.date.format(DateManager.DateFormat.MMM_D_YYYY)
    }

    val result = mutableListOf<Any>()
    map.forEach {
        result.add(it.key)
        it.value.forEach { weather ->
            result.add(weather)
        }
    }

    return result
}

/**
 * Check if valid date, checking if it's not null, not empty string and if is a regular date.
 * @param data the [AggregateData] containing the values to check.
 */
private fun checkDate(data: AggregateData) = !data.dateText.isNullOrEmpty() && DateManager.toDate(data.dateText!!) != null

/**
 * Check if valid temperature, checking if temp, tempMin and tempMax are not null.
 * @param data the [AggregateData] containing the values to check.
 */
private fun checkTemperature(data: AggregateData) = data.main != null && data.main!!.temp != null && data.main!!.tempMin != null && data.main!!.tempMax != null

/**
 * Check if valid weather, checking if the title and subtitle are valid.
 * @param data the [AggregateData] containing the values to check.
 */
private fun checkWeather(data: AggregateData) = !data.weathers.isNullOrEmpty() && !data.weathers!!.filter {
    !it.main.isNullOrEmpty() && !it.description.isNullOrEmpty()
}.isNullOrEmpty()