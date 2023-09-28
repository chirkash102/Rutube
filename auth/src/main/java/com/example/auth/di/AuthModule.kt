package com.example.auth.di

import androidx.room.Room
import com.example.auth.data.RutubeRepository
import com.example.auth.data.RutubeRepositoryImpl
import com.example.auth.roommodel.AppDataBAse
import com.example.auth.viewmodel.RutubeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {

    single<AppDataBAse> {Room.databaseBuilder(get(), AppDataBAse::class.java, "RutubeDataBase").build()}
    single<RutubeRepository> { RutubeRepositoryImpl(get()) }
    viewModel { RutubeViewModel(get()) }
}