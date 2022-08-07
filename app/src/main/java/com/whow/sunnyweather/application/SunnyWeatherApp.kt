package com.whow.sunnyweather.application

import android.app.Application
import android.content.Context

class SunnyWeatherApp: Application() {
    companion object {
        lateinit var context: Context
        const val TOKEN: String = "iLxJQIvkIYDbPLdt"
    }
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}