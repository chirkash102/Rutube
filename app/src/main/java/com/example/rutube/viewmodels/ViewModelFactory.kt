package com.example.rutube.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rutube.data.RutubeRepository

class ViewModelFactory(private val repository: RutubeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RutubeViewModel(repository) as T
    }

}