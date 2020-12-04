package com.dariobrux.openweatherapp.ui.main

import android.content.Context
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.dariobrux.openweatherapp.common.extension.toGroupedByDateList
import com.dariobrux.openweatherapp.data.local.model.CityEntity
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
class MainViewModel @ViewModelInject constructor(@ApplicationContext private val context: Context, private val repository: MainRepository) : ViewModel() {

    /**
     * The weather is mapped into a [Resource] object. Inside it:
     * - the status [com.dariobrux.openweatherapp.data.remote.Resource.Status].
     * - the data that's a Pair with the city name and the
     *   list containing the Pairs with date and [com.dariobrux.openweatherapp.data.local.model.WeatherEntity].
     * - the message.
     */
    val weather = MutableLiveData(Resource(Resource.Status.NONE, Pair<CityEntity?, List<Any>>(null, emptyList()), null))

    /**
     * This is the cached weather. Inside it:
     * - the status [com.dariobrux.openweatherapp.data.remote.Resource.Status].
     * - the data that's a Pair with the city name and the
     *   list containing the Pairs with date and [com.dariobrux.openweatherapp.data.local.model.WeatherEntity].
     * - the message.
     */
    val cachedWeather = liveData {
        val result: Resource<Pair<CityEntity?, List<Any>>>

        val resource = repository.getCachedWeather()

        val status = resource.value!!.status
        val message = resource.value!!.message

        val city = resource.value!!.data?.city
        val groupedList = resource.value!!.data?.toGroupedByDateList(context) ?: emptyList()

        result = Resource(status, Pair(city, groupedList), message)

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

                var status: Resource.Status = Resource.Status.LOADING
                var data: Pair<CityEntity?, List<Any>>? = Pair(weather.value?.data?.first, weather.value?.data?.second ?: emptyList())
                var message: String? = weather.value?.message

                weather.value = Resource(status, data, message)

                delay(1000)

                val value = repository.getWeather(text.toString()).value

                if (value != null) {

                    Timber.d("Status: ${value.status} and message: ${value.message}")

                    val weatherList = value.data?.toGroupedByDateList(context)

                    if (weatherList.isNullOrEmpty()) {

                        status = value.status
                        data = Pair(CityEntity(text.toString()), emptyList())
                        message = null

                    } else {

                        status = Resource.Status.SUCCESS
                        data = Pair(value.data.city, weatherList)
                        message = value.message
                    }
                } else {

                    status = Resource.Status.ERROR
                    data = Pair(CityEntity(text.toString()), emptyList())
                    message = null
                }

                weather.value = Resource(status, data, message)
            }
        }
    }
}