package com.dariobrux.openweatherapp.ui.location

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dariobrux.openweatherapp.common.extension.loadImage
import com.dariobrux.openweatherapp.data.local.model.WeatherEntity
import com.dariobrux.openweatherapp.databinding.ItemDateBinding
import com.dariobrux.openweatherapp.databinding.ItemWeatherBinding


/**
 *
 * Created by Dario Bruzzese on 2/12/2020.
 *
 * This adapter displays the date and the weather conditions.
 *
 */
class LocationAdapter(private val context: Context, private val items: List<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder?>() {

    override fun getItemViewType(position: Int): Int {
        if (items[position] is WeatherEntity) {
            return WEATHER
        } else if (items[position] is String) {
            return DATE
        }
        return -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            WEATHER -> {
                WeatherViewHolder(ItemWeatherBinding.inflate(LayoutInflater.from(context), parent, false))
            }
            else -> {
                DateViewHolder(ItemDateBinding.inflate(LayoutInflater.from(context), parent, false))
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        when (holder.itemViewType) {
            WEATHER -> {
                (item as? WeatherEntity)?.let { (holder as WeatherViewHolder).bind(it) }
            }
            else -> {
                (item as? String)?.let { (holder as DateViewHolder).bind(it) }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class WeatherViewHolder(private val binding: ItemWeatherBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: WeatherEntity) = with(binding) {
            imageWeather.loadImage(item.icon)

        }
    }

    inner class DateViewHolder(private val binding: ItemDateBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) = with(binding) {
            txtDate.text = item
        }
    }

    companion object {
        private const val DATE = 0
        private const val WEATHER = 1
    }
}