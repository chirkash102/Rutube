package com.example.likescreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auth.data.RutubeRepository
import com.example.likescreen.repository.LikeRepository
import com.example.uikit.data.Item
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
        likeRepository.giveLikeVideos(getLogin())
            .onEach { _state.value = it }
            // он ич может провести операци над каждым элементом ничего не возвращая
            .launchIn(viewModelScope)
        // скоуп привязана ку жизненному циклу вью модели
    }

    fun disLike(thumbnail_url: String, title: String) {
        viewModelScope.launch {
            val login = getLogin()
            likeRepository.like(login, thumbnail_url, title)
        }
    }

    fun getLogin(): String {
        val login = authRepository.giveLogin().value
        return if (login!= null)
            login
        else ""
    }
}
