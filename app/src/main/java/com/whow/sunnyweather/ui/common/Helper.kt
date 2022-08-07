package com.whow.sunnyweather.ui.common

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.widget.Toast

inline fun <reified T: Activity> Context.startActivity() {
    val intent = Intent(this, T::class.java)
    startActivity(intent)
}
fun SharedPreferences.open(block: SharedPreferences.Editor.() -> Unit) {
    with(edit()) {
        block()
        apply()
    }
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

infix fun <T> Collection<T>.has(element: T) = contains(element)
