package com.whow.sunnyweather.logic.model

data class Weather(val realTime: RealTimeResponse.RealTime, val daily: DailyResponse.Daily)
