package com.dariobrux.openweatherapp.common.extension

import android.app.Activity
import com.dariobrux.openweatherapp.ui.MainActivity

/**
 *
 * Created by Dario Bruzzese on 1/12/2020.
 *
 * This file contains all the Activity related extended methods.
 *
 */

/**
 * Cast if possible the Activity to MainActivity.
 * @return the [MainActivity].
 */
fun Activity.toMainActivity(): MainActivity? {
    return this as? MainActivity
}