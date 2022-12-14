package uz.ikhtidev.weatherapp.model

data class BaseResponseWeather(
    val current: Current,
    val hourly: List<Hourly>
)
