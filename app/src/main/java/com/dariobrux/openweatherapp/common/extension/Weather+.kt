package com.dariobrux.openweatherapp.common.extension

import com.dariobrux.openweatherapp.common.DateManager
import com.dariobrux.openweatherapp.data.local.model.WeatherEntity
import com.dariobrux.openweatherapp.data.local.model.WeatherInfoEntity
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
 * Convert the [RootData] object to a [WeatherEntity] object.
 * @return the nullable [WeatherEntity].
 */
fun RootData?.toWeatherEntity(): WeatherEntity? {

    this ?: return null
    this.city ?: return null
    this.city!!.name ?: return null
    this.city!!.timezone ?: return null
    if (this.aggregates.isNullOrEmpty()) return null

    return WeatherEntity(
        cityName = this.city!!.name!!,
        weatherInfoList = this.aggregates!!.filter {
            checkDate(it) && checkTemperature(it) && checkWeather(it)
        }.map {
            it.toWeatherInfoEntity()
        }
    )
}

/**
 * Convert the [AggregateData] object to a [WeatherInfoEntity] object.
 * @return the not null [WeatherInfoEntity] object.
 */
fun AggregateData.toWeatherInfoEntity(): WeatherInfoEntity {
    return WeatherInfoEntity(
        date = DateManager.toDate(this.dateText!!)!!,
        temp = this.main!!.temp!!,
        tempMin = this.main!!.tempMin!!,
        tempMax = this.main!!.tempMax!!,
        title = this.weathers!!.first().main!!,
        subtitle = this.weathers!!.first().description!!,
        icon = this.weathers!!.first().icon ?: "",
    )
}

/**
 * Groups the list by date and flattens the result obtaining a list of [Any].
 */
fun WeatherEntity.toGroupedByDateList(): List<Any> {

    val result = mutableListOf<Any>()

    this.weatherInfoList.groupBy {
        it.date.format(DateManager.DateFormat.MMM_D_YYYY)
    }.forEach {
        result.add(it.key)
        result.add(it.value)
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