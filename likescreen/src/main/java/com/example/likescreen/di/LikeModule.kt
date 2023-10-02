package com.example.likescreen.di

import androidx.room.Room
import com.example.auth.roommodel.AppDataBase
import com.example.likescreen.viewmodel.LikeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val likeModule = module {

//    single<AppDataBase> {
//        Room.databaseBuilder(get(), AppDataBase::class.java, "RutubeDataBase").build()
//    }
//    single<LikeDao> {
//        val dataBase = get<AppDataBase>()
//        dataBase.getLikeDao()
//    }
    viewModel { LikeViewModel(get(),get()) }
}
