package com.example.auth.di

import com.example.auth.authdatasource.AuthDatasource
import com.example.auth.authdatasource.LocalAuthDatasource
import com.example.auth.data.RutubeAuthRepositoryImpl
import com.example.auth.data.RutubeRepository
import com.example.auth.viewmodel.RutubeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {

    single<AuthDatasource> { LocalAuthDatasource(get()) }
    single<RutubeRepository> { RutubeAuthRepositoryImpl(get()) }
    viewModel { RutubeViewModel(get()) }
}