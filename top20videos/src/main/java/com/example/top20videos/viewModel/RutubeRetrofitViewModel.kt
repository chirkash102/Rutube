package com.example.top20videos.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rutube.retrofit.RutubeApi
import com.example.top20videos.datamodel.Item
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RutubeRetrofitViewModel(private val rutubeApi: RutubeApi) : ViewModel() {
    private val _state = MutableStateFlow(emptyList<Item>())
    val state = _state.asStateFlow()

    init {
        getVideos()
    }

    fun getVideos() {
        viewModelScope.launch {
            val response = rutubeApi.getRutubeTopVideos()
            val body = response.body()
            if (body != null) {
                val videos = body.results.map {
                    Item(it.thumbnail_url, it.title, body.has_next)
                }
                _state.value = videos

            }

        }
    }
}