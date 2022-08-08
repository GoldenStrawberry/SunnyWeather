package com.whow.sunnyweather.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<M, B : ViewDataBinding>(var items: List<M>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<B>(
            LayoutInflater.from(parent.context),
            getLayoutResId(viewType),
            parent,
            false
        )
        return BaseBindingViewHolder(binding.root)
    }

    abstract fun getLayoutResId(viewType: Int): Int

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = DataBindingUtil.getBinding<B>(holder.itemView)
        onBindItem(binding, items[position])
    }

    abstract fun onBindItem(binding: B?, item: M)

    override fun getItemCount(): Int = items.size

}