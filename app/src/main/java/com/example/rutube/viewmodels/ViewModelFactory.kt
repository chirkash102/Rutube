package com.example.rutube.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rutube.data.RutubeRepository
import com.example.rutube.data.RutubeRepositoryImpl
import com.example.rutube.roommodel.AppDataBAse

class ViewModelFactory (private val repository:RutubeRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RutubeViewModel(repository) as T
    }

}