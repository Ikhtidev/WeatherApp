package uz.ikhtidev.weatherapp.model

data class Hourly(
    val dt: Int,
    val temp: Double,
    val weather: List<Weather>
)