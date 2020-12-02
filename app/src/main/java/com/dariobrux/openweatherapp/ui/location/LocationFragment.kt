package com.dariobrux.openweatherapp.ui.location

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.distinctUntilChanged
import com.dariobrux.openweatherapp.R
import com.dariobrux.openweatherapp.common.extension.toInvisible
import com.dariobrux.openweatherapp.common.extension.toMainActivity
import com.dariobrux.openweatherapp.common.extension.toVisible
import com.dariobrux.openweatherapp.data.remote.Resource
import com.dariobrux.openweatherapp.databinding.FragmentLocationBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 *
 * Created by Dario Bruzzese on 1/12/2020.
 *
 * This Fragment shows the location research.
 *
 */
@AndroidEntryPoint
class LocationFragment : Fragment() {

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

            materialTextField.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_UP) {
                    if (!materialTextField.isExpanded) {
                        materialTextField.expand()
                    } else {
                        materialTextField.reduce()
                    }
                }
                false
            }

            viewModel.bind(editLocation)
        }

        viewModel.weather.distinctUntilChanged().observe(viewLifecycleOwner) { resource ->
            Timber.d("Location ${resource}")
            when (resource.status) {
                Resource.Status.NONE -> {
                    // Do nothing
                }
                Resource.Status.LOADING -> {
                    showLoading()
                }
                Resource.Status.SUCCESS -> {
                    showLocationFound()
                }
                Resource.Status.ERROR -> {
                    showLocationNotFound()
                }
            }
        }
    }

    /**
     * Show the progress on screen during loading state.
     */
    private fun showLoading() {
        binding?.progressLocation?.toVisible()
    }

    /**
     * Show the progress on screen during loading state.
     */
    private fun showLocationNotFound() {
        binding?.progressLocation?.toInvisible()
        Toast.makeText(requireContext(), "Location not found", Toast.LENGTH_LONG).show()
    }

    /**
     * Show the progress on screen during loading state.
     */
    private fun showLocationFound() {
        binding?.progressLocation?.toInvisible()
        Toast.makeText(requireContext(), "Location found", Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

}