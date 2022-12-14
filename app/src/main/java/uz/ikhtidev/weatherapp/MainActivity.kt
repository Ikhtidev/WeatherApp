package uz.ikhtidev.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import uz.ikhtidev.weatherapp.adapter.WeatherAdapter
import uz.ikhtidev.weatherapp.databinding.ActivityMainBinding
import uz.ikhtidev.weatherapp.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    lateinit var binding:ActivityMainBinding
    private val weatherAdapter: WeatherAdapter = WeatherAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.apply {
            progressWeather.observe(this@MainActivity) {
                binding.swipe.isRefreshing = it
                if (it) {
                    binding.progressBarWeather.visibility = View.VISIBLE
                    binding.layoutWeather.visibility = View.INVISIBLE
                } else {
                    binding.progressBarWeather.visibility = View.GONE
                    binding.layoutWeather.visibility = View.VISIBLE
                }
            }
            errorWeather.observe(this@MainActivity) {
                binding.layoutWeather.visibility = View.INVISIBLE
                binding.swipe.isRefreshing = false
                Toast.makeText(this@MainActivity, it, Toast.LENGTH_LONG).show()
            }
            weatherData.observe(this@MainActivity) { weatherData ->
                weatherAdapter.setData(weatherData.hourly.take(24))
                if (!binding.imageCurrentWeather.isAnimating) {
                    binding.imageCurrentWeather.resumeAnimation()
                }
                binding.apply {
                    imageCurrentWeather.setAnimation(weatherImageLotti(weatherData.current.weather[0].description))
                    tempCurrentWeather.text = formatTemperature(weatherData.current.temp)
                    descCurrentWeather.text = weatherData.current.weather[0].main
                    humidityCurrentWeather.text =
                        getString(R.string.humidity) + weatherData.current.humidity + getString(R.string._persent)
                    recyclerWeather.adapter = weatherAdapter
                }
            }
        }

        binding.swipe.setOnRefreshListener {
            loadData()
        }

        loadData()


    }

    private fun weatherImageLotti(strDescWeather: String): Int {
        when (strDescWeather) {
            "broken clouds" -> {
                return R.raw.broken_clouds
            }
            "light rain" -> {
                return R.raw.light_rain
            }
            "haze" -> {
                return R.raw.broken_clouds
            }
            "overcast clouds" -> {
                return R.raw.overcast_clouds
            }
            "moderate rain" -> {
                return R.raw.moderate_rain
            }
            "few clouds" -> {
                return R.raw.few_clouds
            }
            "heavy intensity rain" -> {
                return R.raw.heavy_intentsity
            }
            "clear sky" -> {
                return R.raw.clear_sky
            }
            "scattered clouds" -> {
                return R.raw.scattered_clouds
            }
            else -> {
                return R.raw.unknown
            }
        }

    }

    private fun formatTemperature(temp: Double): String {
        val currentTemp = temp.toInt()
        return if (currentTemp == 0) {
            "$currentTemp°"
        } else {
            if (currentTemp > 0) {
                "+$currentTemp°"
            } else {
                "-$currentTemp°"
            }
        }
    }

    private fun loadData() {
        viewModel.getWeatherData()
    }
}