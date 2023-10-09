package com.example.top20videos.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auth.data.RutubeRepository
import com.example.likescreen.repository.LikeRepository
import com.example.top20videos.repository.Top20Repository
import com.example.uikit.data.Item
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RutubeRetrofitViewModel(
    private val retrofitRepository: Top20Repository,
    private val authRepository: RutubeRepository,
    private val likeRepository: LikeRepository
) : ViewModel() {

    val isAuthorizedState = authRepository.getLogin()
    // стейтфлоу от нулабельных строк


    val viewState =
        flow { emit(retrofitRepository.getTop20Videos()) }// емит - делает 1 событие просмотр
            // комбайн соединяет 2 флоу 1 и 2 -- так можно обернут ьчто угодно
            .combine(likeRepository.giveLikeVideos(getLogin())) { top20, likedVideos ->
                // даю рандомные названи и могу как угодно срастить 2 флоу
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

    fun isLikedVideos(a: List<Item>, b: List<Item>): List<Item> {
        return a.map { item -> item.copy(isLiked = b.find { it.image == item.image } != null) }// копи применяем для того чтобы модно было перезаписать какой -то дата -элемент

    }

    fun getLogin(): String {
        val login = authRepository.getLogin().value
        return if (login!= null)
            login
        else ""
    }
}
