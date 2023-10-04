package com.example.top20videos.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auth.data.RutubeRepository
import com.example.likescreen.repository.LikeRepository
import com.example.top20videos.datamodel.Item
import com.example.top20videos.repository.Top20Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RutubeRetrofitViewModel(
    private val retrofitRepository: Top20Repository,
    private val authRepository: RutubeRepository,
    private val likeRepository: LikeRepository
) : ViewModel() {

    private val _state1 = MutableStateFlow(String())
    val state1 = _state1.asStateFlow()

    val viewState =
        flow { emit(retrofitRepository.getTop20Videos()) }// емит - делает 1 событие просмотр
            // комбайн соединяет 2 флоу 1 и 2 -- так можно обернут ьчто угодно
            .combine(likeRepository.giveLikeVideos(getLogin())) { top20, likedVideos ->
                isLikedVideos(top20, likedVideos)
            }
            .stateIn(
                viewModelScope,
                SharingStarted.Eagerly,
                emptyList()
            ) // превращаю обычгный флоу в стейт

    fun likeAdd(thumbnail_url: String, title: String) {
        viewModelScope.launch {
            val login = getLogin()
            likeRepository.like(login, thumbnail_url, title)
        }
    }

    fun isLikedVideos(a: List<Item>, b: List<com.example.likescreen.datamodel.Item>): List<Item> {
        return a.map { item -> item.copy(isLiked = b.find { it.image == item.image } != null) }

    }

    fun getLogin(): String {
        return authRepository.giveLogin()!!
    }
}
