package com.dariobrux.openweatherapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 *
 * Created by Dario Bruzzese on 1/12/2020.
 *
 * This is the application class declared in Manifest.
 * It's annotated by Hilt.
 */

@HiltAndroidApp
class MainApplication : Application()
