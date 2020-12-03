package com.dariobrux.openweatherapp.ui.location

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.distinctUntilChanged
import androidx.navigation.fragment.NavHostFragment
import com.dariobrux.openweatherapp.R
import com.dariobrux.openweatherapp.common.extension.toInvisible
import com.dariobrux.openweatherapp.common.extension.toMainActivity
import com.dariobrux.openweatherapp.common.extension.toVisible
import com.dariobrux.openweatherapp.data.local.model.WeatherEntity
import com.dariobrux.openweatherapp.data.local.model.WeatherInfoEntity
import com.dariobrux.openweatherapp.data.remote.Resource
import com.dariobrux.openweatherapp.databinding.FragmentLocationBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 *
 * Created by Dario Bruzzese on 1/12/2020.
 *
 * This Fragment shows the location research.
 *
 */
@AndroidEntryPoint
class LocationFragment : Fragment(), WeatherAdapter.OnItemSelectedListener {

    /**
     * View binder. Destroy it in onDestroyView avoiding memory leaks.
     */
    private var binding: FragmentLocationBinding? = null

    /**
     * The ViewModel that handles all this Fragment logic.
     */
    private val viewModel: LocationViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLocationBinding.inflate(inflater, container, false)
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
                showLocationFound(it.data!!.first, it.data.second)
            }
        }

        viewModel.weather.distinctUntilChanged().observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.NONE -> {
                    // Do nothing
                }
                Resource.Status.LOADING -> {
                    showLoading()
                }
                Resource.Status.ERROR -> {
                    binding?.progressLocation?.toInvisible()
                }
                Resource.Status.SUCCESS -> {
                    showLocationFound(it.data!!.first, it.data.second)
                }
            }
        }
    }

    /**
     * Show the progress on screen during loading state.
     */
    private fun showLoading() {
        with(binding!!) {
            progressLocation.toVisible()
        }
    }

    /**
     * Dismiss the progress if it's loading. Show the city name and the weather
     * in the RecyclerView.
     * @param city the name of the city.
     * @param items the list of [WeatherEntity] to show.
     */
    private fun showLocationFound(city: String, items: List<Any>) {
        binding?.run {
            txtCityName.text = city
            progressLocation.toInvisible()
            recyclerWeather.adapter = LocationAdapter(requireContext(), items, this@LocationFragment)
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