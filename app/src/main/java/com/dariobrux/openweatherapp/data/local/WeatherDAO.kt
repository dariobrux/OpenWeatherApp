package com.dariobrux.openweatherapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dariobrux.openweatherapp.data.local.model.WeatherEntity

/**
 *
 * Created by Dario Bruzzese on 3/12/2020.
 *
 * This interface is the DAO.
 * It's responsible for defining the methods that access the database.
 *
 */

@Dao
interface WeatherDAO {

    /**
     * Get the weather stored.
     * @return the [WeatherEntity].
     */
    @Query("Select * from weather")
    fun getWeather(): WeatherEntity

    /**
     * Insert the weather in the database.
     * @param weatherEntity: the [WeatherEntity] to insert.
     * Use the replacing strategy to override each existing item.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeather(weatherEntity: WeatherEntity)
}