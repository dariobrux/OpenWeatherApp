package com.dariobrux.openweatherapp.data.remote

import com.dariobrux.openweatherapp.data.remote.model.RootData
import retrofit2.Response
import javax.inject.Inject

/**
 *
 * Created by Dario Bruzzese on 1/12/2020.
 *
 * This class get the result from the API Service and map it into a more useful object.
 *
 */
class WeatherApiHelper @Inject constructor(private val service: WeatherService) : ApiHelper() {

    /**
     * Get the [RootData] with the high level info about the weather.
     * @param cityName the name of the city.
     * @param appId the id of the registered user.
     * @return the [RootData] mapped into a [Response] object.
     */
    suspend fun getWeather(cityName: String, appId: String) = getResult { service.getWeather(cityName, appId) }
}