package com.dariobrux.openweatherapp.ui.location

import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dariobrux.openweatherapp.common.extension.toFlattenGroupedDateWeatherList
import com.dariobrux.openweatherapp.data.remote.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 *
 * Created by Dario Bruzzese on 1/12/2020.
 *
 * This is the ViewModel that contains all the logic and correlations between
 * Fragment and Repository.
 *
 */
class LocationViewModel @ViewModelInject constructor(private val repository: LocationRepository) : ViewModel() {

    /**
     * The weather is mapped into a [Resource] object. Inside it:
     * - the status [com.dariobrux.openweatherapp.data.remote.Resource.Status].
     * - the list containing the the date and the [com.dariobrux.openweatherapp.data.local.model.WeatherEntity].
     * - the message.
     */
    val weather = MutableLiveData(Pair("", Resource(Resource.Status.NONE, emptyList<Any>(), null)))


    /**
     * Bind the location EditText observing its changing text.
     * Emit the city name and the list to all observer.
     * @param editText the EditText for the location.
     */
    fun bind(editText: EditText) {
        editText.doOnTextChanged { text, _, _, _ ->
            CoroutineScope(Dispatchers.Main).launch {

                val cityName = text.toString()

                weather.value = Pair(cityName, Resource(Resource.Status.LOADING, weather.value?.second?.data, weather.value?.second?.message))

                delay(1000)

                repository.getWeather(cityName).value?.let {
                    val weatherList = it.data?.toFlattenGroupedDateWeatherList()

                    weather.value = Pair(cityName, Resource(it.status, weatherList, it.message))
                }
            }
        }
    }
}