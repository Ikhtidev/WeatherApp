package uz.ikhtidev.weatherapp.api

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.ikhtidev.weatherapp.utils.Constants

object NetworkManager {

    // for baseUrl: BASE_URL_WEATHER
    fun getApiInstanceWeather(): Api {
        var api: Api? = null
        if (api == null){
            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_WEATHER)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .build()
            api = retrofit.create(Api::class.java)
        }
        return api!!
    }

}