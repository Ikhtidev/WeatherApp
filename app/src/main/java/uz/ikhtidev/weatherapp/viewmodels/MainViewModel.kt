package uz.ikhtidev.weatherapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.ikhtidev.weatherapp.model.BaseResponseWeather
import uz.ikhtidev.weatherapp.repository.MainRepository

class MainViewModel : ViewModel() {
    private val mainRepository = MainRepository()

    private var _progressWeather = MutableLiveData<Boolean>()
    val progressWeather: LiveData<Boolean>
        get() {
            return _progressWeather
        }

    private var _errorWeather = MutableLiveData<String>()
    val errorWeather: LiveData<String>
        get() {
            return _errorWeather
        }

    private var _weatherData = MutableLiveData<BaseResponseWeather>()
    val weatherData: LiveData<BaseResponseWeather>
        get() {
            return _weatherData
        }

    fun getWeatherData() {
        mainRepository.getWeatherData(_progressWeather, _errorWeather, _weatherData)
    }

}