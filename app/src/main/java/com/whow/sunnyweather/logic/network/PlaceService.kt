package com.whow.sunnyweather.logic.network

import com.whow.sunnyweather.application.SunnyWeatherApp
import com.whow.sunnyweather.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceService {

    @GET("v2/place?taken=${SunnyWeatherApp.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>
}