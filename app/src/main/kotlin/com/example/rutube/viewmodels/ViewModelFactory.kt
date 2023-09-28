package com.example.rutube.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.auth.data.RutubeRepository
import com.example.auth.viewmodel.RutubeViewModel
import com.example.top20videos.repository.Top20Repository
import com.example.top20videos.viewModel.RutubeRetrofitViewModel

class ViewModelFactory(private val repository: RutubeRepository, private val rutubeApi: Top20Repository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when (modelClass) {
            RutubeViewModel::class.java -> RutubeViewModel(
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
