package com.app.mobileapptask.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.fragment.app.Fragment

@SuppressLint("ShowToast")
fun Activity.toast(message: String) {
    makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.toast(message: String) {
    activity?.toast(message)
}