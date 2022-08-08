package com.whow.sunnyweather.ui.common

import android.content.Context
import android.view.Gravity
import android.widget.Toast

/**
 * 显示短时间的Toast
 */
fun String?.showShortToast(context: Context) = takeIf { it.isNullOrBlank().not() }?.also {
    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
}


/**
 * 显示长时间的Toast
 */
fun String?.showLongToast(context: Context) = takeIf { it.isNullOrBlank().not() }?.also {
    Toast.makeText(context, this, Toast.LENGTH_LONG).show()
}

/**
 * 居中显示短时间的Toast
 */
fun String?.showShortToastInCenter(context: Context) = takeIf { it.isNullOrBlank().not() }?.also {
    Toast.makeText(context, this, Toast.LENGTH_SHORT).apply {
        setGravity(Gravity.CENTER, 0, 0)
        show()
    }
}

/**
 * 居中显示长时间的Toast
 */
fun String?.showLongToastInCenter(context: Context) = takeIf { it.isNullOrBlank().not() }?.also {
    Toast.makeText(context, this, Toast.LENGTH_LONG).apply {
        setGravity(Gravity.CENTER, 0, 0)
        show()
    }
}