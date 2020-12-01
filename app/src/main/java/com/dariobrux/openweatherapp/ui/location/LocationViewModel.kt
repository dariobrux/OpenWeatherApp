package com.dariobrux.openweatherapp.ui.location

import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dariobrux.openweatherapp.data.local.model.WeatherEntity
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

    val weather = MutableLiveData(Resource(Resource.Status.NONE, emptyList<WeatherEntity>(), null))

    /**
     * Bind the location EditText observing its changing text.
     * @param editText the EditText for the location.
     */
    fun bind(editText: EditText) {
        editText.doOnTextChanged { text, _, _, _ ->
            CoroutineScope(Dispatchers.Main).launch {
                delay(1000)
                weather.value = repository.getWeather(text.toString()).value
            }
        }
    }
}