package com.app.mobileapptask.constant

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor


object AppConstant {
    const val DATABASE_NAME = "database"
    const val SHARED_PREF = "shared_pref"
    const val EMAIL_ADDRESS = "email_address"

    fun saveUserEmail(context: Context, emailAddress: String) {
        val editor: Editor = context.getSharedPreferences(SHARED_PREF, MODE_PRIVATE).edit()
        editor.putString(EMAIL_ADDRESS, emailAddress)
        editor.apply()
    }

    fun getUserEmail(context: Context) : String {
        val prefs: SharedPreferences = context.getSharedPreferences(SHARED_PREF, MODE_PRIVATE)
        return prefs.getString(EMAIL_ADDRESS, "").toString()
    }
}