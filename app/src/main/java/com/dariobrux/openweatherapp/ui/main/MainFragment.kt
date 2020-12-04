package com.dariobrux.openweatherapp.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.dariobrux.openweatherapp.R
import com.dariobrux.openweatherapp.common.extension.toInvisible
import com.dariobrux.openweatherapp.common.extension.toMainActivity
import com.dariobrux.openweatherapp.common.extension.toVisible
import com.dariobrux.openweatherapp.data.local.model.CityEntity
import com.dariobrux.openweatherapp.data.local.model.WeatherEntity
import com.dariobrux.openweatherapp.data.local.model.WeatherInfoEntity
import com.dariobrux.openweatherapp.data.remote.Resource
import com.dariobrux.openweatherapp.databinding.FragmentMainBinding
import com.dariobrux.openweatherapp.ui.main.adapter.GroupedAdapter
import com.dariobrux.openweatherapp.ui.main.adapter.WeatherAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 *
 * Created by Dario Bruzzese on 1/12/2020.
 *
 * This Fragment shows the location research.
 *
 */
@AndroidEntryPoint
class MainFragment : Fragment(), WeatherAdapter.OnItemSelectedListener {

    /**
     * View binder. Destroy it in onDestroyView avoiding memory leaks.
     */
    private var binding: FragmentMainBinding? = null

    /**
     * The ViewModel that handles all this Fragment logic.
     */
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requireActivity().toMainActivity()?.setStatusBarColor(R.color.indigo_400)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        with(binding!!) {
            viewModel.bind(editLocation)
            progressLocation.toInvisible()
        }

        viewModel.cachedWeather.observe(viewLifecycleOwner) {
            if (it.status == Resource.Status.SUCCESS) {
                showLocationFound(it.data!!.first!!, it.data.second)
            }
        }

        viewModel.weather.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.NONE -> {
                    // Do nothing
                }
                Resource.Status.LOADING -> {
                    showLoading()
                    dismissNoResult()
                }
                Resource.Status.ERROR -> {
                    dismissLoading()
                    showNoResults(it.data?.first?.cityName)
                }
                Resource.Status.SUCCESS -> {
                    showLocationFound(it.data!!.first!!, it.data.second)
                }
            }
        }
    }

    /**
     * Show the No Results label or nothing if the [cityName] is null or empty.
     * @param cityName the name of the city
     */
    private fun showNoResults(cityName: String?) {
        cityName?.let {
            binding?.let {
                it.txtNoResults.toVisible()
                it.txtNoResults.text = getString(R.string.no_results_format, cityName)
            }
        }
    }

    /**
     * Dismiss the No Results label from the screen.
     */
    private fun dismissNoResult() {
        binding?.txtNoResults?.toInvisible()
    }

    /**
     * Dismiss the progress on screen.
     */
    private fun dismissLoading() {
        binding?.progressLocation?.toInvisible()
    }

    /**
     * Show the progress on screen.
     */
    private fun showLoading() {
        binding?.progressLocation?.toVisible()

    }

    /**
     * Dismiss the progress if it's loading. Show the city name and the weather
     * in the RecyclerView.
     * @param city the name of the city.
     * @param items the list of [WeatherEntity] to show.
     */
    private fun showLocationFound(city: CityEntity, items: List<Any>) {
        binding?.run {
            txtCityName.text = city.cityName
            progressLocation.toInvisible()
            recyclerWeather.adapter = GroupedAdapter(requireContext(), items, this@MainFragment)
            txtSunrise.text = getString(R.string.sunrise_format, city.sunrise)
            txtSunset.text = getString(R.string.sunset_format, city.sunset)
        }
    }

    override fun onDestroyView() {
        binding?.recyclerWeather?.adapter = null
        binding = null
        super.onDestroyView()
    }

    /**
     * Invoked when an item has been selected.
     * @param item the [WeatherInfoEntity] item selected.
     */
    override fun onItemSelected(item: WeatherInfoEntity) {
        NavHostFragment.findNavController(requireParentFragment()).navigate(R.id.action_locationFragment_to_infoFragment, Bundle().apply {
            putSerializable("weather", item)
        })
    }

}