package com.dariobrux.openweatherapp.ui.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dariobrux.openweatherapp.common.Constants
import com.dariobrux.openweatherapp.common.toWeatherEntityList
import com.dariobrux.openweatherapp.data.local.model.WeatherEntity
import com.dariobrux.openweatherapp.data.remote.Resource
import com.dariobrux.openweatherapp.data.remote.WeatherApiHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

/**
 *
 * Created by Dario Bruzzese on 1/12/2020.
 *
 * This class is the repository that handles the communication
 * between the ViewModel and the Restful API.
 *
 */
class LocationRepository @Inject constructor(private val api: WeatherApiHelper) {

    /**
     * Get the weather from Restful API.
     * @param cityName the name of the city.
     * @return the [WeatherEntity] object mapped into a [Resource], inside a [LiveData].
     */
    suspend fun getWeather(cityName: String): LiveData<Resource<List<WeatherEntity>>> {

        val result = MutableLiveData(Resource(Resource.Status.LOADING, emptyList<WeatherEntity>(), null))

        withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {

            var status = Resource.Status.LOADING
            var data = emptyList<WeatherEntity>()
            var message: String? = ""

            kotlin.runCatching {

                api.getWeather(cityName, Constants.APP_ID)

            }.onSuccess {

                status = it.status
                data = it.data.toWeatherEntityList()
                message = it.message

            }.onFailure {

                status = Resource.Status.ERROR
                data = emptyList()
                message = "Problems while retrieve the data."

                Timber.w(message)
            }

            withContext(Dispatchers.Main) {
                result.value = Resource(status, data, message)
            }

        }

        return result
    }
}