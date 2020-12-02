package com.dariobrux.openweatherapp.common.extension

import android.view.View

/**
 *
 * Created by Dario Bruzzese on 2/12/2020.
 *
 * This file contains all the View related extended methods.
 *
 */

/**
 * Change the visibility to VISIBLE
 */
fun View.toVisible() {
    this.visibility = View.VISIBLE
}

/**
 * Change the visibility to INVISIBLE
 */
fun View.toInvisible() {
    this.visibility = View.INVISIBLE
}