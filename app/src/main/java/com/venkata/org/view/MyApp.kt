package com.venkata.org.view

import android.app.Application
import com.venkata.org.model.sharedPreference.SharedPreferenceManager

class MyApp:Application() {

    override fun onCreate() {
        super.onCreate()
        SharedPreferenceManager.init(this)
    }
}