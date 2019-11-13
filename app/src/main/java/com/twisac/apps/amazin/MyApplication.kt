package com.twisac.apps.amazin

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication

import com.activeandroid.ActiveAndroid

class MyApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        //Initializing Active Android
        ActiveAndroid.initialize(this)

    }
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(newBase)
        MultiDex.install(this)
    }
}