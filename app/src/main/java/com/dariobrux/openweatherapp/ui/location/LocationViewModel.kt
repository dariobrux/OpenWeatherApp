package com.dariobrux.openweatherapp.ui.location

import android.content.Context
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.dariobrux.openweatherapp.common.extension.toGroupedByDateList
import com.dariobrux.openweatherapp.data.remote.Resource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 *
 * Created by Dario Bruzzese on 1/12/2020.
 *
 * This is the ViewModel that contains all the logic and correlations between
 * Fragment and Repository.
 *
 */
class LocationViewModel @ViewModelInject constructor(@ApplicationContext private val context: Context, private val repository: LocationRepository) : ViewModel() {

    /**
     * The weather is mapped into a [Resource] object. Inside it:
     * - the status [com.dariobrux.openweatherapp.data.remote.Resource.Status].
     * - the data that's a Pair with the city name and the
     *   list containing the Pairs with date and [com.dariobrux.openweatherapp.data.local.model.WeatherEntity].
     * - the message.
     */
    val weather = MutableLiveData(Resource(Resource.Status.NONE, Pair("", emptyList<Any>()), null))

    /**
     * This is the cached weather. Inside it:
     * - the status [com.dariobrux.openweatherapp.data.remote.Resource.Status].
     * - the data that's a Pair with the city name and the
     *   list containing the Pairs with date and [com.dariobrux.openweatherapp.data.local.model.WeatherEntity].
     * - the message.
     */
    val cachedWeather = liveData {
        val result: Resource<Pair<String, List<Any>>>

        val resource = repository.getCachedWeather()

        val status = resource.value!!.status
        val message = resource.value!!.message

        val cityName = resource.value!!.data?.cityName ?: ""
        val groupedList = resource.value!!.data?.toGroupedByDateList(context) ?: emptyList()

        result = Resource(status, Pair(cityName, groupedList), message)

        emit(result)
    }

    /**
     * Bind the location EditText observing its changing text.
     * Emit the city name and the list to all observer.
     * @param editText the EditText for the location.
     */
    fun bind(editText: EditText) {
        editText.doOnTextChanged { text, _, _, _ ->
            CoroutineScope(Dispatchers.Main).launch {

                val cityName = text.toString()

                var status: Resource.Status = Resource.Status.LOADING
                var data: Pair<String, List<Any>>? = Pair(cityName, weather.value?.data?.second ?: emptyList())
                var message: String? = weather.value?.message

                weather.value = Resource(status, data, message)

                delay(1000)

                val value = repository.getWeather(cityName).value

                if (value != null) {

                    Timber.d("Status: ${value.status} and message: ${value.message}")

                    val weatherList = value.data?.toGroupedByDateList(context)

                    if (weatherList.isNullOrEmpty()) {

                        status = value.status
                        data = null
                        message = null

                    } else {

                        status = Resource.Status.SUCCESS
                        data = Pair(cityName, weatherList)
                        message = value.message
                    }
                } else {

                    status = Resource.Status.ERROR
                    data = null
                    message = null
                }

                weather.value = Resource(status, data, message)
            }
        }
    }
}