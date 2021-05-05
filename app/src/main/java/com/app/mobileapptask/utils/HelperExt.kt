package com.app.mobileapptask.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

@SuppressLint("ShowToast")
fun Activity.toast(message: String) {
    makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.toast(message: String) {
    activity?.toast(message)
}

fun <T, VH : RecyclerView.ViewHolder> ListAdapter<T, VH>.updateList(list: List<T>?) {
    // ListAdapter<>.submitList() contains (stripped):
    //  if (newList == mList) {
    //      // nothing to do
    //      return;
    //  }
    this.submitList(if (list == this.currentList) list.toList() else list)
}