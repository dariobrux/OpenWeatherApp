package com.dariobrux.openweatherapp.ui.location

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dariobrux.openweatherapp.common.DateManager
import com.dariobrux.openweatherapp.common.extension.format
import com.dariobrux.openweatherapp.common.extension.loadImage
import com.dariobrux.openweatherapp.data.local.model.WeatherEntity
import com.dariobrux.openweatherapp.databinding.ItemSingleWeatherBinding
import java.util.*


/**
 *
 * Created by Dario Bruzzese on 3/12/2020.
 *
 * This adapter displays the weather conditions.
 *
 */
class WeatherAdapter(private val context: Context, private val items: List<WeatherEntity>) : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder?>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(ItemSingleWeatherBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class WeatherViewHolder(private val binding: ItemSingleWeatherBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: WeatherEntity) = with(binding) {
            imageWeather.loadImage(item.weatherInfo.icon)
            txtWeather.text = item.weatherInfo.subtitle.capitalize(Locale.getDefault())
            txtTime.text = item.weatherInfo.date.format(DateManager.DateFormat.H_MM_AA)
            txtTemp.text = item.weatherInfo.temp.toString()
        }
    }
}