package com.fadynearby.application

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.fadynearby.repository.SharedPreferenceRepository
import com.fadynearby.ui.utils.Utils

class MyApplication : MultiDexApplication() {

    init {
        instance = this
    }

    companion object {
        private var instance: MyApplication? = null
        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        val pref = SharedPreferenceRepository(this)
        pref.getPeriodicRate() ?: pref.savePeriodicRate(Utils.REALTIME)
    }


}