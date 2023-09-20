package com.example.oom_practice1.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rutube.roommodel.AppDataBAse
import com.example.rutube.viewmodels.RutubeViewModel

class ViewModelFactory (private val RutubeDataBase: AppDataBAse):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RutubeViewModel(RutubeDataBase) as T
    }

}