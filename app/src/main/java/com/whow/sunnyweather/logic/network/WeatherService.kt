package com.whow.sunnyweather.logic.network

import com.whow.sunnyweather.application.SunnyWeatherApp
import com.whow.sunnyweather.logic.model.DailyResponse
import com.whow.sunnyweather.logic.model.RealTimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {

    /**
     * url 参数在url '?' 之前，参数用@Path引入
     * url 参数在url '?' 之后，参数用 @Query引入
     */

    @GET("v2.5/${SunnyWeatherApp.TOKEN}/{lng},{lat}/realtime.json")
    fun getRealTimeWeather(@Path("lng") lng: String, @Path("lat") lat: String): Call<RealTimeResponse>

    @GET("v2.5/${SunnyWeatherApp.TOKEN}/{lng},{lat}/daily.json")
    fun getDailyWeather(@Path("lng") lng: String, @Path("lat") lat: String): Call<DailyResponse>
}