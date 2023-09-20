package com.example.rutube.application

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.oom_practice1.viewmodels.ViewModelFactory
import com.example.rutube.roommodel.AppDataBAse

class App :Application() {
lateinit var viewModelFactory: ViewModelProvider.Factory
    override fun onCreate() {
        super.onCreate()
        val RutubeDataBase = Room.databaseBuilder(this, AppDataBAse::class.java,"RutubeDataBase").build()
        viewModelFactory = ViewModelFactory(RutubeDataBase)
    }
}