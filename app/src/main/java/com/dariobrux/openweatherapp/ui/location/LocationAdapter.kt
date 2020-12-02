package com.dariobrux.openweatherapp.ui.location

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dariobrux.openweatherapp.common.extension.loadImage
import com.dariobrux.openweatherapp.data.local.model.WeatherEntity
import com.dariobrux.openweatherapp.databinding.ItemWeatherBinding
import com.dariobrux.openweatherapp.ui.location.LocationAdapter.WeatherViewHolder
import java.text.DateFormat

/**
 *
 * Created by Dario Bruzzese on 2/12/2020.
 *
 * This adapter displays the weather conditions.
 *
 */
class LocationAdapter(private val context: Context, private val items: List<WeatherEntity>) : RecyclerView.Adapter<WeatherViewHolder?>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(ItemWeatherBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class WeatherViewHolder(private val binding: ItemWeatherBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: WeatherEntity) = with(binding) {
            txtDate.text = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.SHORT).format(item.date)
            imageWeather.loadImage(item.icon)

        }
    }
}