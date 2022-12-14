package uz.ikhtidev.weatherapp

import android.app.Application

class MyApp : Application() {
    companion object{
        lateinit var app: MyApp
    }

    override fun onCreate() {
        super.onCreate()
        app = this

    }
}