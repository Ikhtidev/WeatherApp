package uz.ikhtidev.weatherapp.model

data class Current(
    val humidity: Int,
    val temp: Double,
    val weather: List<Weather>
)