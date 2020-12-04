package com.dariobrux.openweatherapp.ui.main

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dariobrux.openweatherapp.R
import com.dariobrux.openweatherapp.ui.MainActivity
import junit.framework.TestCase
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainFragmentTest : TestCase() {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @After
    fun cleanup() {
        scenario.close()
    }

    @Test
    fun testIsMaterialTextFieldDisplayed() {
        scenario = launchActivity()
        Thread.sleep(3500)
        onView(withId(R.id.materialTextField)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(1500)
    }

    @Test
    fun testFillEditText() {
        scenario = launchActivity()
        Thread.sleep(3500)
        onView(withId(R.id.materialTextField)).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.editLocation)).perform(replaceText("Genoa"))
        Thread.sleep(1500)
    }
}