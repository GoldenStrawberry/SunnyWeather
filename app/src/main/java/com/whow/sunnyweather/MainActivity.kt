package com.whow.sunnyweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.whow.sunnyweather.databinding.ActivityMainBinding
import com.whow.sunnyweather.ui.place.PlaceFragment

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        initView()
    }

    private fun initView() {
        // 加载fragment
        supportFragmentManager.commit {
            add(R.id.frame_layout, PlaceFragment.newInstance())
        }
    }
}