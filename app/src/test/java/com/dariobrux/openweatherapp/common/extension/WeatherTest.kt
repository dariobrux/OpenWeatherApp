package com.dariobrux.openweatherapp.common.extension

import com.dariobrux.openweatherapp.data.remote.model.*
import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner::class)
class WeatherTest : TestCase() {

    private var main = MainData(
        temp = 20.0,
        feelsLike = 20.0,
        tempMin = 15.0,
        tempMax = 25.0,
        pressure = 10.0,
        seaLevel = 10.0,
        groundLevel = 10.0,
        humidity = 10.0
    )

    private var weatherData = WeatherData(
        id = 1,
        main = "Cloud",
        description = "Many clouds on the sky",
        icon = "ic"
    )

    private var cloudData = CloudData(
        percentage = 10
    )

    private var windDate = WindData(
        speed = 10.0,
        degrees = 10
    )

    private var rainData = RainData(
        volume = 50.0
    )

    private var snowData = SnowData(
        volume = 50.0
    )

    private var sysData = SysData(
        partOfDay = "n"
    )

    private var aggregateData = AggregateData(
        dateMillis = 1606932000,
        main = main,
        weathers = listOf(weatherData),
        cloud = cloudData,
        wind = windDate,
        visibility = 0,
        pop = 0.0,
        rain = rainData,
        snow = snowData,
        sys = sysData,
        dateText = "2020-12-02 18:00:00"
    )

    private val coordData = CoordData(
        lat = 44.4222,
        lon = 8.9052
    )

    private val cityData = CityData(
        id = 1,
        name = "Genoa",
        coord = coordData,
        country = "IT",
        population = 883180,
        timezone = 3600,
        sunrise = 1606891301,
        sunset = 1606923916
    )

    private var rootData = RootData(
        code = 200,
        message = "",
        countItems = 1,
        aggregates = listOf(aggregateData),
        city = cityData
    )

    @Test
    fun testFromRootDataToWeatherEntity() {
        val result = rootData.toWeatherEntity()
        assertEquals(result?.city?.cityName, rootData.city?.name)
        assertEquals(result?.weatherInfoList?.size, rootData.aggregates?.size)
    }

    @Test
    fun testFromCityDataToCityEntity() {
        val result = cityData.toCityEntity()
        assertEquals(result.cityName, cityData.name)
    }

    @Test
    fun testFromListAggregateDataToWeatherInfoEntityList() {
        val result = rootData.aggregates?.map {
            it.toWeatherInfoEntity()
        }
        assertEquals(result?.size, rootData.aggregates?.size)
    }

    @Test
    fun testFromAggregateDataToWeatherInfoEntity() {
        val result = aggregateData.toWeatherInfoEntity()
        assertEquals(result.title, aggregateData.weathers?.firstOrNull()?.main)
        assertEquals(result.subtitle, aggregateData.weathers?.firstOrNull()?.description)
        assertEquals(result.icon, aggregateData.weathers?.firstOrNull()?.icon)
        assertEquals(result.humidity, aggregateData.main?.humidity)
        assertEquals(result.temp, aggregateData.main?.temp)
        assertEquals(result.tempMin, aggregateData.main?.tempMin)
        assertEquals(result.tempMax, aggregateData.main?.tempMax)
    }
}