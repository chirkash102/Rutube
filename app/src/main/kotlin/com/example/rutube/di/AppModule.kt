package com.example.rutube.di

import com.example.auth.viewmodel.RutubeViewModel
import com.example.top20videos.retrofit.RutubeApi
import com.example.top20videos.retrofit.RutubeRetrofit
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

//val appModule = module {
////    single <RutubeRepository>{ RutubeRepositoryImpl(get())}
////    single <AppDataBAse> { RoomDatabase }
////single<RutubeApi> {
////    RutubeRetrofit
////}
////viewModel { RutubeViewModel(get()) }
////}