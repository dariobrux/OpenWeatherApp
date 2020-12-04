package com.dariobrux.openweatherapp.common.extension

import com.dariobrux.openweatherapp.ui.MainActivity
import com.dariobrux.openweatherapp.ui.main.MainFragment
import junit.framework.TestCase
import org.junit.Test
import org.mockito.Mockito

class ActivityTest : TestCase() {

    @Test
    fun testToMainActivity() {
        val mainFragment = Mockito.mock(MainFragment::class.java)
        Mockito.`when`(mainFragment.requireActivity()).thenReturn(MainActivity())
        assertTrue(mainFragment.requireActivity().toMainActivity() is MainActivity)
    }
}