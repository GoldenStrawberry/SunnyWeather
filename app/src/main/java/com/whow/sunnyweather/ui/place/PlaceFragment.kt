package com.whow.sunnyweather.ui.place

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.whow.sunnyweather.R
import com.whow.sunnyweather.databinding.FragmentPlaceBinding
import com.whow.sunnyweather.logic.model.Place
import com.whow.sunnyweather.ui.common.showToast
import com.whow.sunnyweather.ui.weather.WeatherViewModel

class PlaceFragment : Fragment() {

    private val placeViewModel by viewModels<PlaceViewModel>()
    private val weatherViewModel by viewModels<WeatherViewModel>()
    private lateinit var adapter: PlaceAdapter

    companion object {
        @JvmStatic
        fun newInstance() = PlaceFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val placeBinding = FragmentPlaceBinding.inflate(layoutInflater)
        initView(placeBinding)
        initData(placeBinding)
        return placeBinding.root
    }

    private fun initView(placeBinding: FragmentPlaceBinding) {
        val layoutManager = LinearLayoutManager(context)
        adapter = PlaceAdapter(placeViewModel.placeList)
        placeBinding.apply {
            placeList.layoutManager = layoutManager
            placeList.addItemDecoration(DividerItemDecoration(context, layoutManager.orientation))
            placeList.adapter = adapter
        }
        placeBinding.searchPlace.addTextChangedListener {
            val content = it.toString()
            if (content.isNotEmpty()) {
                placeViewModel.searchPlaces(content)
            } else {
                placeBinding.placeList.visibility = View.GONE
            }
        }
        adapter.setOnClickItemListener(object : PlaceAdapter.OnClickItemLister{
            override fun onClickItem(item: Place) {
                weatherViewModel.refreshWeather(item.location.lng, item.location.lat)
            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initData(placeBinding: FragmentPlaceBinding) {
        placeViewModel.placeLiveData.observe(viewLifecycleOwner) { result ->
            val list = result.getOrNull()
            if (list == null) {
                context?.let { showToast(it, "未能查询到任何地点") }
            }
            list?.let {
                placeBinding.placeList.visibility = View.VISIBLE
                placeViewModel.placeList.clear()
                placeViewModel.placeList.addAll(it)
                adapter.notifyDataSetChanged()
            }
        }
        weatherViewModel.weatherLiveData.observe(viewLifecycleOwner) {
            val list = it.getOrNull()
            Log.d("Huwei", list.toString())
        }
    }
}