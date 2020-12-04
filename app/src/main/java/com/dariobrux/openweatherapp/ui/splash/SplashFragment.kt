package com.dariobrux.openweatherapp.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.dariobrux.openweatherapp.R
import com.dariobrux.openweatherapp.common.extension.toMainActivity
import com.dariobrux.openweatherapp.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel

/**
 *
 * Created by Dario Bruzzese on 1/12/2020.
 *
 * This is the splash fragment, the first fragment displayed.
 *
 */
@AndroidEntryPoint
class SplashFragment : Fragment() {

    /**
     * View binder. Destroy it in onDestroyView avoiding memory leaks.
     */
    private var binding: FragmentSplashBinding? = null

    /**
     * The Coroutine Scope in which apply the splash timer. Starts in
     * onResume and cancels in onPause.
     */
    private val activityScope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requireActivity().toMainActivity()?.setStatusBarColor(R.color.blue_gray_900)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    override fun onResume() {
        super.onResume()
        with(binding!!.imgSplash) {
            scaleX = 0.2f
            scaleY = 0.2f
            alpha = 0f
            animate().scaleX(1f).scaleY(1f).alpha(1f).setDuration(2000).setInterpolator(OvershootInterpolator()).withEndAction {
                NavHostFragment.findNavController(requireParentFragment()).navigate(R.id.action_splashFragment_to_locationFragment)
            }.start()
        }
    }

    override fun onPause() {
        activityScope.cancel()
        super.onPause()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}