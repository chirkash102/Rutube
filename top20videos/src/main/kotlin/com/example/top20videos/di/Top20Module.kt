package com.example.top20videos.di

import com.example.auth.data.RutubeRepository
import com.example.likescreen.repository.LikeRepository
import com.example.likescreen.repository.LikeRepositoryImpl
import com.example.top20videos.repository.Top20Repository
import com.example.top20videos.repository.Top20RepositoryImpl
import com.example.top20videos.retrofit.RutubeApi
import com.example.top20videos.retrofit.RutubeRetrofit
import com.example.top20videos.viewModel.RutubeRetrofitViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val top20module = module {
    single<RutubeApi> { RutubeRetrofit.rutubeApi }
    single<Top20Repository> { Top20RepositoryImpl(get()) }
    single<LikeRepository> { LikeRepositoryImpl(get()) }

   // viewModel { RutubeRetrofitViewModel(get())}
    viewModel {
        RutubeRetrofitViewModel(get(), get(), get())
    }
}