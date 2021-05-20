package com.dmgpersonal.myweatherapp

class RepositoryImpl: Repository {
    override fun getWeatherFromServer(): Weather {
        return Weather(
            City("Moscow", 55.755826, 37.617299900000035),
            temperature = 25,
            feelsLike = 27,
            WeatherState.Wind(speed = 5, Direction.EAST),
            humidity = 35,
            WeatherState.Clouds(Intensity.NONE),
            WeatherState.Snow(Intensity.NONE),
            WeatherState.Rain(Intensity.NONE),
            WeatherState.Mist(Intensity.NONE, visibility = -1),
            isRain = false,
            isSnow = false,
            isMist = false
        )
    }

    override fun getWeatherFromLocalStorage(): Weather {
        return Weather(
            getDefaultCity(),
            temperature = 0,
            feelsLike = 0,
            WeatherState.Wind(speed = 0, Direction.NOTHING),
            humidity = 0,
            WeatherState.Clouds(Intensity.NONE),
            WeatherState.Snow(Intensity.NONE),
            WeatherState.Rain(Intensity.NONE),
            WeatherState.Mist(Intensity.NONE, visibility = -1),
            isRain = false,
            isSnow = false,
            isMist = false
        )
    }
}