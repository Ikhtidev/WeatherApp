package uz.ikhtidev.weatherapp.repository

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.ikhtidev.weatherapp.model.BaseResponseWeather
import uz.ikhtidev.weatherapp.MyApp
import uz.ikhtidev.weatherapp.R
import uz.ikhtidev.weatherapp.api.NetworkManager

class MainRepository {

    fun getWeatherData(progress: MutableLiveData<Boolean>, error: MutableLiveData<String>, success: MutableLiveData<BaseResponseWeather>){
        progress.value = true
        NetworkManager.getApiInstanceWeather().getWeatherData("39.761824", "64.422623", "daily","metric", "37ad43a2a1025d7ed0b614b6ff91548d").enqueue(object : Callback<BaseResponseWeather?> {
            override fun onResponse(call: Call<BaseResponseWeather?>, response: Response<BaseResponseWeather?>) {
                progress.value = false
                if (response.isSuccessful){
                    success.value = response.body()
                }
                else{
                    error.value = response.message()
                }
            }

            override fun onFailure(call: Call<BaseResponseWeather?>, t: Throwable) {
                progress.value = true
                error.value = MyApp.app.getString(R.string.network_error)
            }
        })
    }
}