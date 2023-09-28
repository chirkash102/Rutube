package com.example.rutube.application

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.auth.data.RutubeRepositoryImpl
import com.example.auth.roommodel.AppDataBAse
import com.example.rutube.di.appModule
import com.example.rutube.viewmodels.ViewModelFactory
import com.example.top20videos.repository.Top20RepositoryImp
import com.example.top20videos.retrofit.RutubeRetrofit
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@App)
            modules(listOf(appModule))
        }

        val rutubeDataBase =
            Room.databaseBuilder(this, AppDataBAse::class.java, "RutubeDataBase").build()
        val repository = RutubeRepositoryImpl(rutubeDataBase)
        val rutubeApi = RutubeRetrofit.rutubeApi
        val top20repository = Top20RepositoryImp(rutubeApi)
        viewModelFactory = ViewModelFactory(
            repository,
            top20repository
        )
    }
}
