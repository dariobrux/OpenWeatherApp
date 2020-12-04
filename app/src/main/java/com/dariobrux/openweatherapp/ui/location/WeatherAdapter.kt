package com.dariobrux.openweatherapp.ui.location

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dariobrux.openweatherapp.R
import com.dariobrux.openweatherapp.common.DateManager
import com.dariobrux.openweatherapp.common.extension.format
import com.dariobrux.openweatherapp.common.extension.loadImage
import com.dariobrux.openweatherapp.data.local.model.WeatherInfoEntity
import com.dariobrux.openweatherapp.databinding.ItemSingleWeatherBinding
import java.util.*
import kotlin.math.roundToInt

/**
 *
 * Created by Dario Bruzzese on 3/12/2020.
 *
 * This adapter displays the weather conditions.
 *
 */
class WeatherAdapter(
    private val context: Context,
    private val items: List<WeatherInfoEntity>,
    private val listener: OnItemSelectedListener?
) : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder?>() {

    /**
     * This interface contains the callback to invoke when an item has been selected.
     */
    interface OnItemSelectedListener {

        /**
         * Invoked when an item has been selected.
         * @param item the [WeatherInfoEntity] item selected.
         */
        fun onItemSelected(item: WeatherInfoEntity)
    }

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
        fun bind(item: WeatherInfoEntity) = with(binding) {

            cardRoot.setOnClickListener {
                listener?.onItemSelected(item)
            }

            imageWeather.loadImage(item.icon)
            txtWeather.text = item.subtitle.capitalize(Locale.getDefault())
            txtTime.text = DateManager.toDate(item.date)!!.format(DateManager.DateFormat.H_MM_AA)
            txtTemp.text = context.getString(R.string.degree_format, item.temp.roundToInt())
        }
    }
}