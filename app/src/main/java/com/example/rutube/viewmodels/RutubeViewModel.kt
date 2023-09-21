package com.example.rutube.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rutube.roommodel.AppDataBAse
import com.example.rutube.roommodel.RutubeMembers
import com.example.rutube.roommodel.ViewEvents
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class RutubeViewModel(private val rutubeDataBase: AppDataBAse) : ViewModel() {

    private val eventsFlow = MutableSharedFlow<ViewEvents>()
    fun getEventsFlow() = eventsFlow.asSharedFlow()

    val repository = rutubeDataBase.getDao()
    fun delete(login: String, pass: String) {
        viewModelScope.launch {
            if (repository.isAlreadyExist(login) != null) {
                repository.delete(RutubeMembers(login, pass))
            }
        }
    }

    fun regNewUser(login: String, pass: String) {
        viewModelScope.launch {
            if (repository.isAlreadyExist(login) != null) {
                eventsFlow.emit(ViewEvents.Error("Пользователь уже существует"))
            } else {
                repository.insert(RutubeMembers(login, pass))
                eventsFlow.emit(ViewEvents.SuccessAuth(RutubeMembers(login, pass)))
            }
        }
    }

    fun login(login: String, pass: String) {
        viewModelScope.launch {
            if (repository.validateLogin(login, pass) != null) {
                eventsFlow.emit(ViewEvents.SuccessAuth(RutubeMembers(login, pass)))
            } else {
                eventsFlow.emit(ViewEvents.Error("Неверный логин или пароль"))
            }
        }
    }
}
