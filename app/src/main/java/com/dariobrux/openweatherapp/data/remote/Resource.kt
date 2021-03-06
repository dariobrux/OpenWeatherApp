package com.dariobrux.openweatherapp.data.remote

/**
 *
 * Created by Dario Bruzzese on 1/12/2020.
 *
 */
data class Resource<out T>(val status: Status, val data: T?, val message: String?) {

    enum class Status {
        NONE,
        LOADING,
        SUCCESS,
        ERROR
    }

    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, message)
        }
    }
}