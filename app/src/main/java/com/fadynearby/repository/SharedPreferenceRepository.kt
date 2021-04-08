package com.fadynearby.repository

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

class SharedPreferenceRepository(application: Application) {
    var preference: SharedPreferences? = application.getSharedPreferences("periodicRate", Context.MODE_PRIVATE)

    fun getPeriodicRate() = preference?.getString("periodicUpdate", null)

    fun savePeriodicRate(periodicUpdate: String) {
        return preference?.edit()?.putString("periodicUpdate", periodicUpdate)!!.apply()
    }

}