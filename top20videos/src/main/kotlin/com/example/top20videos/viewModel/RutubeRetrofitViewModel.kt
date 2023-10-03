package com.example.top20videos.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auth.data.RutubeRepository

import com.example.likescreen.repository.LikeRepository
import com.example.top20videos.datamodel.Item
import com.example.top20videos.repository.Top20Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RutubeRetrofitViewModel(
    private val retrofitRepository: Top20Repository,
    private val authRepository: RutubeRepository,
    private val likeRepository: LikeRepository
) : ViewModel() {
    private val _state = MutableStateFlow(emptyList<Item>())
    val state = _state.asStateFlow()

    private val _state1 = MutableStateFlow(String())
    val state1 = _state1.asStateFlow()


    private val _stateLike = MutableStateFlow(Boolean)
    val statelike = _stateLike.asStateFlow()

    init {
        getVideos()
    }

    val likeLIst = mutableListOf<Item>()
    fun likeAdd1(thumbnail_url: String, title: String) {
        viewModelScope.launch {
            val login = getLogin()
            likeLIst.add(Item(thumbnail_url, title, hasNext = true))
            Log.d("LikeAdd", "Item added to likeList: thumbnail_url=$thumbnail_url, title=$title")
        }
    }

    fun likeAdd(thumbnail_url: String, title: String) {
        viewModelScope.launch {
            val login = getLogin()
            likeRepository.like(login, thumbnail_url, title)
        }
    }

    fun getVideos() {
        viewModelScope.launch {
            val login = getLogin()
            _state1.value = login!!
            val response = retrofitRepository.getTop20Videos()
            _state.value = response
        }
    }

    suspend fun getLogin(): String {
        return authRepository.giveLogin()!!
    }
}
