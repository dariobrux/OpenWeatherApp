package com.dariobrux.openweatherapp.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

/**
 *
 * Created by Dario Bruzzese on 1/12/2020.
 *
 * This singleton object is a bucket from where we will get all the dependencies from.
 *
 */
@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

}
