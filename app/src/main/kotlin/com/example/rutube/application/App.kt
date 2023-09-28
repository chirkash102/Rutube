package com.example.rutube.application

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.auth.data.RutubeRepositoryImpl
import com.example.auth.roommodel.AppDataBAse
import com.example.rutube.viewmodels.ViewModelFactory
import com.example.top20videos.repository.Top20Repository
import com.example.top20videos.repository.Top20RepositoryImp
import com.example.top20videos.retrofit.RutubeApi
import com.example.top20videos.retrofit.RutubeRetrofit
import retrofit2.create

class App : Application() {
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override fun onCreate() {
        super.onCreate()

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
