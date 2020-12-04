package com.dariobrux.openweatherapp.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.dariobrux.openweatherapp.R
import com.dariobrux.openweatherapp.common.DateManager
import com.dariobrux.openweatherapp.common.extension.*
import com.dariobrux.openweatherapp.data.local.model.WeatherInfoEntity
import com.dariobrux.openweatherapp.databinding.FragmentInfoBinding
import com.dariobrux.openweatherapp.ui.location.LocationViewModel
import java.util.*

/**
 *
 * Created by Dario Bruzzese on 3/12/2020.
 *
 * This Fragment shows the specific info about the single time-weather.
 *
 */
class InfoFragment : DialogFragment() {

    /**
     * The ViewModel that handles all this Fragment logic.
     */
    private val viewModel: LocationViewModel by viewModels()

    /**
     * View binder. Destroy it in onDestroyView avoiding memory leaks.
     */
    private var binding: FragmentInfoBinding? = null

    private var weatherInfo: WeatherInfoEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)

        weatherInfo = requireArguments().getSerializable("weather") as? WeatherInfoEntity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding!!) {
            weatherInfo?.let { item ->

                DateManager.toDate(item.date)?.let { date ->
                    txtTime.text = when {
                        date.isToday() -> {
                            getString(R.string.at_format, getString(R.string.today), date.format(DateManager.DateFormat.H_MM_AA))
                        }
                        date.isTomorrow() -> {
                            getString(R.string.at_format, getString(R.string.tomorrow), date.format(DateManager.DateFormat.H_MM_AA))
                        }
                        else -> {
                            date.format(DateManager.DateFormat.MMM_D_H_MM_AA)
                        }
                    }
                }

                imageWeather.loadImage(item.icon)
                txtDescription.text = item.subtitle.capitalize(Locale.getDefault())
                txtHumidity.text = getString(R.string.humidity_format, item.humidity.toInt())

                if (DateManager.toDate(item.date)?.isToday() == true) {
                    txtMin.toGone()
                    txtTemp.text = getString(R.string.temp_format, item.temp.toInt())
                    txtMax.toGone()
                } else {
                    txtMin.text = getString(R.string.min_format, item.tempMin.toInt())
                    txtTemp.toGone()
                    txtMax.text = getString(R.string.max_format, item.tempMax.toInt())
                }
            }
        }
    }

}