package uz.ikhtidev.weatherapp.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import uz.ikhtidev.weatherapp.model.BaseResponseWeather

interface Api {
    @GET("onecall")
    fun getWeatherData(@Query("lat")lat: String, @Query("lon")lon: String,@Query("exclude")exclude: String, @Query("units")units: String, @Query("appid")appid: String): Call<BaseResponseWeather>
}