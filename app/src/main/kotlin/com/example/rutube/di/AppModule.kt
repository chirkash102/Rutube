package com.example.rutube.di

import com.example.auth.viewmodel.RutubeViewModel
import com.example.top20videos.repository.Top20Repository
import com.example.top20videos.repository.Top20RepositoryImp
import com.example.top20videos.retrofit.RutubeApi
import com.example.top20videos.retrofit.RutubeRetrofit
import com.example.top20videos.viewModel.RutubeRetrofitViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

