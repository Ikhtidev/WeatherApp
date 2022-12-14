package uz.ikhtidev.weatherapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.ikhtidev.weatherapp.R
import uz.ikhtidev.weatherapp.databinding.ItemWeatherBinding
import uz.ikhtidev.weatherapp.model.Hourly
import java.text.SimpleDateFormat
import java.util.*

class WeatherAdapter: RecyclerView.Adapter<WeatherAdapter.ItemHolder>() {

    private var items = emptyList<Hourly>()

    inner class ItemHolder(val binding: ItemWeatherBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(ItemWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = items[position]

        holder.binding.textViewTemperature.text = formatTemperature(item.temp)
        holder.binding.lottieAnimationView.setAnimation(weatherImageLotti(item.weather[0].description))
        holder.binding.textViewHour.text = timestampToHour(item.dt.toLong())

    }

    override fun getItemCount(): Int = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(items: List<Hourly>) {
        this.items = items
        notifyDataSetChanged()
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

    @SuppressLint("SimpleDateFormat")
    private fun timestampToHour(timestamp: Long): String {
        val format = SimpleDateFormat("HH:mm")
        format.timeZone = TimeZone.getTimeZone("Asia/Samarkand")
        val netDate = Date(timestamp * 1000)
        return format.format(netDate)
    }

    private fun formatTemperature(temp: Double): String {
        val currentTemp = temp.toInt()
        return if (currentTemp == 0) {
            "$temp°"
        } else {
            if (currentTemp > 0) {
                "+$temp°"
            } else {
                "-$temp°"
            }
        }
    }
}