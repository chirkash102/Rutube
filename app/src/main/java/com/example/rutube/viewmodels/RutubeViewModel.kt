package com.example.rutube.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rutube.data.RutubeRepository
import com.example.rutube.roommodel.ViewEvents
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class RutubeViewModel(private val repository: RutubeRepository) : ViewModel() {

    private val eventsFlow = MutableSharedFlow<ViewEvents>()
    fun getEventsFlow() = eventsFlow.asSharedFlow()

    fun delete(login: String, pass: String) {
        viewModelScope.launch {
            val result = repository.delete(login, pass)
            delay(1000)
            //need to show loadingAnimation
            eventsFlow.emit(result)

        }
    }

    fun signUP(login: String, pass: String) {
        viewModelScope.launch {
            val result = repository.signUp(login, pass)
            eventsFlow.emit(result)

        }
    }

    fun signIn(login: String, pass: String) {
        viewModelScope.launch {
            val result = repository.signIn(login, pass)
            eventsFlow.emit(result)
        }
    }
}
