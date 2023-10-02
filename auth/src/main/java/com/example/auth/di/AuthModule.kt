package com.example.auth.di

import androidx.room.Room
import com.example.auth.data.RutubeRepository
import com.example.auth.data.RutubeAuthRepositoryImpl
import com.example.auth.viewmodel.RutubeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {

    single<RutubeRepository> { RutubeAuthRepositoryImpl(get()) }
    viewModel { RutubeViewModel(get()) }
}