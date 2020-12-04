package com.dariobrux.openweatherapp.common.extension

import com.dariobrux.openweatherapp.common.DateManager
import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.text.SimpleDateFormat
import java.util.*

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner::class)
class DateTest : TestCase() {

    @Test
    fun testTestFormat() {
        val sdf = SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.ENGLISH)
        val today: Date = sdf.parse("2020-12-02 02:30 am")!!
        assertEquals(today.format(DateManager.DateFormat.H_MM_AA), "2:30 am")
        assertEquals(today.format(DateManager.DateFormat.MMM_D_YYYY), "Dec 2, 2020")
        assertEquals(today.format(DateManager.DateFormat.YYYY_MM_DD_HH_MM_SS), "2020-12-02 02:30:00")
        assertEquals(today.format(DateManager.DateFormat.MMM_D_H_MM_AA), "Dec 2, 2:30 am")
    }
}