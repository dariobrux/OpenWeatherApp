package com.dariobrux.openweatherapp.data.remote

import com.dariobrux.openweatherapp.data.remote.model.RootData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *
 * Created by Dario Bruzzese on 1/12/2020.
 *
 * Interface for Retrofit that contains the declaration of the API to call.
 *
 */
interface WeatherService {

    /**
     * Get the [RootData] with the high level info about the weather.
     * @param cityName the name of the city.
     * @param appId the id of the registered user.
     * @return the [RootData] mapped into a [Response] object.
     */
    @GET("data/2.5/forecast")
    suspend fun getWeather(@Query("q") cityName: String, @Query("appid") appId: String): Response<RootData>
}