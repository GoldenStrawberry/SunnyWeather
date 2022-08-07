package com.whow.sunnyweather.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.whow.sunnyweather.logic.model.Place
import com.whow.sunnyweather.logic.network.Repository

class PlaceViewModel: ViewModel() {

    private val searchLiveData = MutableLiveData<String>()
    // 对界面上显示的数据进行缓存
    val placeList = ArrayList<Place>()
    // Transformations.map() 转换ViewModel中的liveData
    // Transformations.switchMap() 转换函数(转换另外的方法获取的liveData),用来观察searchLiveData对象
    val placeLiveData = Transformations.switchMap(searchLiveData) { query ->
        Repository.searchPlaces(query)
    }

    fun searchPlaces(query: String) {
        searchLiveData.value = query
    }
}