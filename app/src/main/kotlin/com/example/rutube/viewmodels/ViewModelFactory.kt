package com.example.rutube.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.auth.data.RutubeRepository
import com.example.top20videos.retrofit.RutubeApi
import com.example.top20videos.viewModel.RutubeRetrofitViewModel

class ViewModelFactory(private val repository: com.example.auth.data.RutubeRepository, private val rutubeApi: RutubeApi) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when (modelClass) {
            com.example.auth.viewmodel.RutubeViewModel::class.java -> com.example.auth.viewmodel.RutubeViewModel(
                repository
            )
           RutubeRetrofitViewModel::class.java -> RutubeRetrofitViewModel(
                rutubeApi
            )
            else -> throw IllegalArgumentException()
        }
        return viewModel as T
    }

}
