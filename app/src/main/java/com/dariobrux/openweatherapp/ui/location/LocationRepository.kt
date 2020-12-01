package com.dariobrux.openweatherapp.ui.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dariobrux.openweatherapp.common.Constants
import com.dariobrux.openweatherapp.data.model.RootData
import com.dariobrux.openweatherapp.data.remote.ApiHelper
import com.dariobrux.openweatherapp.data.remote.Resource
import com.dariobrux.openweatherapp.data.remote.WeatherApiHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
     * @return the [RootData] object mapped into a [Resource], inside a [LiveData].
     */
    fun getWeather(cityName: String): LiveData<Resource<RootData>> {

        val result: MutableLiveData<Resource<RootData>> = MutableLiveData()

        CoroutineScope(Dispatchers.IO).launch {

            val rootData : RootData

            kotlin.runCatching {
                api.getWeather(cityName, Constants.APP_ID)
            }.onSuccess {
                true
                 it
            }.onFailure {
                Timber.w("Problems while retrieve the data.")
            }
        }

        return result
    }
}