package com.dariobrux.openweatherapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dariobrux.openweatherapp.data.local.converter.DateConverter
import com.dariobrux.openweatherapp.data.local.converter.WeatherInfoConverter
import com.dariobrux.openweatherapp.data.local.model.WeatherEntity
import com.dariobrux.openweatherapp.data.local.model.WeatherInfoEntity

/**
 *
 * Created by Dario Bruzzese on 3/12/2020.
 *
 * This class is the representation of the database.
 *
 */

@Database(
    exportSchema = false, version = 1, entities = [
        WeatherEntity::class,
        WeatherInfoEntity::class
    ]
)
@TypeConverters(
    WeatherInfoConverter::class,
    DateConverter::class
)
abstract class WeatherDatabase : RoomDatabase() {

    companion object {
        private const val DB_NAME = "weather.db"

        private var instance: WeatherDatabase? = null

        @Synchronized
        fun getInstance(context: Context): WeatherDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext, WeatherDatabase::class.java, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance!!
        }
    }

    abstract fun weatherDAO(): WeatherDAO

}