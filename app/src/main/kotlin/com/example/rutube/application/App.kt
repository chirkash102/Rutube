package com.example.rutube.application

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.auth.data.RutubeRepositoryImpl
import com.example.auth.roommodel.AppDataBAse
import com.example.rutube.viewmodels.ViewModelFactory
import com.example.top20videos.retrofit.RutubeRetrofit

class App : Application() {
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override fun onCreate() {
        super.onCreate()
        val rutubeDataBase =
            Room.databaseBuilder(this, AppDataBAse::class.java, "RutubeDataBase").build()
        val repository = RutubeRepositoryImpl(rutubeDataBase)
        viewModelFactory = ViewModelFactory(
            repository,
            RutubeRetrofit.rutubeApi
        )
    }
}
