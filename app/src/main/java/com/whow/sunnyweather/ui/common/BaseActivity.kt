package com.whow.sunnyweather.ui.common

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding

abstract  class BaseActivity<VM: BaseViewModel, VD: ViewDataBinding> : AppCompatActivity() {

    protected abstract val viewDataBinding: VD
    protected abstract val viewModel: VM
}