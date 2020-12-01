package com.dariobrux.openweatherapp.ui.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dariobrux.openweatherapp.R
import com.dariobrux.openweatherapp.common.toMainActivity
import com.dariobrux.openweatherapp.databinding.FragmentLocationBinding
import com.dariobrux.openweatherapp.databinding.FragmentSplashBinding

/**
 *
 * Created by Dario Bruzzese on 1/12/2020.
 *
 * This Fragment shows the location research.
 *
 */
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

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

}