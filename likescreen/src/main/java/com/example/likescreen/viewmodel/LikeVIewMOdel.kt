package com.example.likescreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auth.data.RutubeRepository
import com.example.likescreen.datamodel.Item
import com.example.likescreen.repository.LikeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


class LikeViewModel(
    private val authRepository: RutubeRepository,
    private val likeRepository: LikeRepository
) : ViewModel() {
    private val _state = MutableStateFlow(emptyList<Item>())
    val state = _state.asStateFlow()
    private val _state1 = MutableStateFlow(String())
    val state1 = _state1.asStateFlow()

    init {
        val login = authRepository.giveLogin()!!
        likeRepository.giveLikeVideos(login)
            .onEach { _state.value = it }
            .launchIn(viewModelScope)
    }

//    fun getVideos() {
//        viewModelScope.launch {
//            val login = authRepository.giveLogin()!!
//            val videos = likeRepository.giveLikeVideos(login)
//            _state.value = videos
//            _state1.value = login
//        }
//    }

}
