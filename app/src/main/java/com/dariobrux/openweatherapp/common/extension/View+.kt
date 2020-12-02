package com.dariobrux.openweatherapp.common.extension

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.dariobrux.openweatherapp.common.Constants

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

/**
 * Load the icon from network into the ImageView.
 * @param icon the icon name.
 */
fun ImageView.loadImage(icon: String) {
    Glide.with(this.context).load(Constants.ICON_BASE_URL + Constants.PATH_NETWORK_ICON + icon + Constants.ICON_FORMAT).into(this)
}