package com.example.eac2jsonplaceholder

import android.app.Application
import com.example.eac2jsonplaceholder.data.AppContainer
import com.example.eac2jsonplaceholder.data.DefaultAppContainer

class JsonPHApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}