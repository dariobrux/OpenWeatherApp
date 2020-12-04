package com.dariobrux.openweatherapp.ui.main

import android.content.Context
import androidx.lifecycle.MutableLiveData
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MainViewModelTest : TestCase() {

    private lateinit var openMocks: AutoCloseable
    private lateinit var viewModel: MainViewModel

    @Mock
    private lateinit var repository: MainRepository

    @Mock
    private lateinit var context: Context

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    public override fun setUp() {
        super.setUp()
        openMocks = MockitoAnnotations.openMocks(this)
        viewModel = MainViewModel(context, repository)
    }

    override fun tearDown() {
        super.tearDown()
        openMocks.close()
    }

    @Test
    fun testGetCachedExistingData() = runBlocking {
        Mockito.`when`(repository.getWeather("Genova")).thenReturn(MutableLiveData())
        assertTrue(viewModel.weather.value != null)
    }
}