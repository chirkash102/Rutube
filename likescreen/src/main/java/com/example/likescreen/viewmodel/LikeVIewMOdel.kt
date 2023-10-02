package com.example.likescreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auth.data.RutubeRepository
import com.example.likescreen.datamodel.Item
import com.example.likescreen.repository.LikeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class LikeViewModel(private val authRepository: RutubeRepository, private val likeRepository: LikeRepository) : ViewModel() {
    private val _state = MutableStateFlow(emptyList<Item>())
    val state = _state.asStateFlow()
init {
    getVideos()
}
    fun getVideos() {
        viewModelScope.launch {
            val login = authRepository.giveLogin()!!
        val videos =likeRepository.giveLikeVideos(login).map { Item(it.thumbnailUrl,it.title) }
            _state.value = videos
        }
    }

}
