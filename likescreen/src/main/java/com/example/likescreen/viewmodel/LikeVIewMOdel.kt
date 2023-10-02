package com.example.likescreen.viewmodel

import androidx.lifecycle.ViewModel
import com.example.auth.data.LikeRepository
import com.example.auth.data.RutubeRepository

class LikeViewModel(private val authRepository: RutubeRepository, private val likeRepository: LikeRepository) : ViewModel() {

}
