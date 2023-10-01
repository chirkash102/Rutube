package com.example.auth.di

import androidx.room.Room
import com.example.auth.data.RutubeRepository
import com.example.auth.data.RutubeAuthRepositoryImpl
import com.example.auth.roommodel.AppDataBase
import com.example.auth.viewmodel.RutubeViewModel
import com.example.likescreen.rommtable.LikeDao
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {

    single<AppDataBase> {
        Room.databaseBuilder(get(), AppDataBase::class.java, "RutubeDataBase").build()
    }
    single<LikeDao> {
        val dataBase = get<AppDataBase>()
        dataBase.getLikeDao()
    }
    single<RutubeRepository> { RutubeAuthRepositoryImpl(get()) }
    viewModel { RutubeViewModel(get()) }
}