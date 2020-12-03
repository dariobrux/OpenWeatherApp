package com.dariobrux.openweatherapp.di

import android.content.Context
import com.dariobrux.openweatherapp.BuildConfig
import com.dariobrux.openweatherapp.common.Constants
import com.dariobrux.openweatherapp.data.local.WeatherDAO
import com.dariobrux.openweatherapp.data.local.WeatherDatabase
import com.dariobrux.openweatherapp.data.remote.WeatherApiHelper
import com.dariobrux.openweatherapp.data.remote.WeatherService
import com.dariobrux.openweatherapp.ui.location.LocationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
        OkHttpClient
            .Builder()
            .hostnameVerifier { _, _ -> true }
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .hostnameVerifier { _, _ -> true }
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

    @Singleton
    @Provides
    fun provideLocationRepository(apiHelper: WeatherApiHelper, dao: WeatherDAO) = LocationRepository(apiHelper, dao)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = WeatherDatabase.getInstance(appContext)

    @Singleton
    @Provides
    fun provideDAO(db: WeatherDatabase) = db.weatherDAO()
}
