package com.dmgpersonal.myweatherapp

sealed class WeatherState {
    data class Rain(val intensity: Intensity) : WeatherState()
    data class Snow(val intensity: Intensity) : WeatherState()
    data class Wind(val speed: Int, val direction: Direction) : WeatherState()
    data class Mist(val intensity: Intensity, val visibility: Int) : WeatherState()
    data class Clouds(val intensity: Intensity) : WeatherState()
}
