package com.dariobrux.openweatherapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dariobrux.openweatherapp.R
import dagger.hilt.android.AndroidEntryPoint

/**
 *
 * Created by Dario Bruzzese on 1/12/2020.
 *
 * This is the main activity, where the application starts its
 * navigation.
 *
 * It is annotated by AndroidEntryPoint to integrate Hilt in this
 * activity.
 *
 */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}