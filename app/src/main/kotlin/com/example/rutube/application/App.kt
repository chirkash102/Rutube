package com.example.rutube.application

import android.app.Application
import com.example.auth.di.authModule
import com.example.top20videos.di.top20module
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(top20module, authModule))
        }
    }
}
