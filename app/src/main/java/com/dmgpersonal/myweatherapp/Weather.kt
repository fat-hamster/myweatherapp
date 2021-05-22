package com.dmgpersonal.myweatherapp

data class Weather(
    val city: City = getDefaultCity(),
    val temperature: Int = 0,
    val feelsLike: Int = 0,
    val wind: WeatherState.Wind,
    val humidity: Int = 0,
    val clouds: WeatherState.Clouds,
    val snow: WeatherState.Snow,
    val rain: WeatherState.Rain,
    val mist: WeatherState.Mist,
    val isRain: Boolean,
    val isSnow: Boolean,
    val isMist: Boolean
)

fun getDefaultCity(): City = City("Nothing", 0.0, 0.0)
