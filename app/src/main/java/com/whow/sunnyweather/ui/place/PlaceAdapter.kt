package com.whow.sunnyweather.ui.place

import com.whow.sunnyweather.R
import com.whow.sunnyweather.databinding.PlaceItemBinding
import com.whow.sunnyweather.logic.model.Place
import com.whow.sunnyweather.ui.common.BaseAdapter
import java.text.FieldPosition

class PlaceAdapter(list: List<Place>): BaseAdapter<Place, PlaceItemBinding>(list) {

    private var onClickItemLister: OnClickItemLister? = null

    override fun getLayoutResId(viewType: Int): Int {
        return R.layout.place_item
    }

    override fun onBindItem(binding: PlaceItemBinding?, item: Place) {
        binding?.place = item
        binding?.executePendingBindings()
        binding?.root?.setOnClickListener {
            onClickItemLister?.onClickItem(item)
        }
    }

    fun setOnClickItemListener(listener: OnClickItemLister) {
        onClickItemLister = listener
    }

    interface OnClickItemLister {
        fun onClickItem(item: Place)
    }

}