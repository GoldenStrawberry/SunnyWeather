package com.whow.sunnyweather.logic.network

import android.util.Log
import androidx.lifecycle.liveData
import com.whow.sunnyweather.logic.model.Weather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

/**
 * 仓库层的作用是判断调用方请求的数据加载途径：
 * 1、本地数据源（缓存数据）
 * 2、网络数据源
 *
 */
object Repository {

    fun searchPlaces(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            val placeResponse = SunnyWeatherNetwork.searchPlaces(query)
            if (placeResponse.status == "ok") {
                val places = placeResponse.places
                // Kotlin内置的Result.success()包装返回结果
                Result.success(places)
            } else {
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
        // 类似于调用liveData的setValue()方法来通知数据变化
        emit(result)
    }

    fun refreshWeather(lng: String, lat: String) = fire(Dispatchers.IO) {
        coroutineScope {
            val deferredDaily = async {
                SunnyWeatherNetwork.getDailyWeather(lng, lat)
            }
            val deferredRealTime = async {
                SunnyWeatherNetwork.getRealTimeWeather(lng, lat)
            }
            val realTimeResponse = deferredRealTime.await()
            val dailyResponse = deferredDaily.await()
            Log.d("Huwei", "realTimeResponse = ${realTimeResponse.result}")
            if (realTimeResponse.status == "ok" && dailyResponse.status == "ok") {
                val weather = Weather(realTimeResponse.result.realTime, dailyResponse.result.daily)
                Result.success(weather)
            } else{
                Result.failure(RuntimeException("realtime response status is ${realTimeResponse.status}" +
                        "daily response status is ${dailyResponse.status}"))
            }
        }
    }

    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData<Result<T>>(context) {
        val result = try {
            block()
        } catch (exception: Exception) {
            Result.failure<T>(exception)
        }
        emit(result)
    }
}