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
            delay(500)
            //need to show loadingAnimation
            if (result) {
                eventsFlow.emit(ViewEvents.SuccessDelete(true))
            } else eventsFlow.emit(ViewEvents.Error("Пользователя не существует, быдло"))
        }
    }

    fun signUp(login: String, pass: String) {
        viewModelScope.launch {
            val result = repository.signUp(login, pass)
            if (result != null) {
                eventsFlow.emit(ViewEvents.SuccessRegistration(result))
            } else eventsFlow.emit(ViewEvents.Error("Пользователь уже существует, быдло"))
        }
    }

    fun signIn(login: String, pass: String) {
        viewModelScope.launch {
            val result = repository.signIn(login, pass)
            if (result != null) {
                eventsFlow.emit(ViewEvents.SuccessAuth(result))
            } else eventsFlow.emit(ViewEvents.Error("Неверный логин или пароль, лох"))
        }
    }
}