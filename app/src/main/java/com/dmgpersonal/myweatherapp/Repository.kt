package com.dmgpersonal.myweatherapp

interface Repository {
    fun getWeatherFromServer(): Weather
    fun getWeatherFromLocalStorage(): Weather // Last weather state if not server connection
}