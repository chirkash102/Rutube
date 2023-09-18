package com.example.rutube
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rutube.data.Item
import com.example.rutube.retrofit.RutubeRetrofit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RutubeViewModel : ViewModel() {
    private val _state = MutableStateFlow(emptyList<Item>())
    val state = _state.asStateFlow()

    init {
        getVideos()
    }

    fun getVideos() {
        viewModelScope.launch {
            val response = RutubeRetrofit.rutubeApi.getRutubeTopVideos()
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