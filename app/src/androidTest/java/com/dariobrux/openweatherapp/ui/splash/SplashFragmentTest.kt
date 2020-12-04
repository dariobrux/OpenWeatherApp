package com.dariobrux.openweatherapp.ui.splash

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dariobrux.openweatherapp.R
import com.dariobrux.openweatherapp.ui.MainActivity
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SplashFragmentTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @After
    fun cleanup() {
        scenario.close()
    }

    @Test
    fun testSplashIsDisplayed() {
        scenario = launchActivity()
        Espresso.onView(withId(R.id.imgSplash)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testSplashDuration() {
        scenario = launchActivity()
        Espresso.onView(withId(R.id.imgSplash)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(3000)
        Espresso.onView(withId(R.id.containerMain)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}