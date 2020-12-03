package com.dariobrux.openweatherapp.ui.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

/**
 *
 * Created by Dario Bruzzese on 3/12/2020.
 *
 * This class is the ItemDecoration useful for the RecyclerView for weather cards.
 */
class WeatherSpaceItemDecoration(private val space: Int) : ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        parent.adapter?.let {
            val position = parent.getChildAdapterPosition(view)

            outRect.top = space
            outRect.bottom = space

            if (position < it.itemCount - 1) {
                outRect.right = space
            }
        }
    }
}