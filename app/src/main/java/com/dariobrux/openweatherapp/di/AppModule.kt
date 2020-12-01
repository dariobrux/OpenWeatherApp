package com.dariobrux.openweatherapp.di

import android.provider.SyncStateContract
import com.dariobrux.openweatherapp.BuildConfig
import com.dariobrux.openweatherapp.common.Constants
import com.dariobrux.openweatherapp.data.remote.WeatherApiHelper
import com.dariobrux.openweatherapp.data.remote.WeatherService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 *
 * Created by Dario Bruzzese on 1/12/2020.
 *
 * This singleton object is a bucket from where we will get all the dependencies from.
 *
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = Constants.BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideWeatherService(retrofit: Retrofit): WeatherService = retrofit.create(WeatherService::class.java)

    @Provides
    @Singleton
    fun provideWeatherApiHelper(service: WeatherService) = WeatherApiHelper(service)
}
