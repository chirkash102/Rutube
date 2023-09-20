package com.example.rutube.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rutube.roommodel.AppDataBAse
import com.example.rutube.roommodel.RutubeMemberState
import com.example.rutube.roommodel.RutubeMembers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RutubeViewModel(private val rutubeDataBase: AppDataBAse) : ViewModel() {


    private val _state = MutableStateFlow<RutubeMemberState?>(null)
    val state = _state.asStateFlow()


    fun delete(login: String, pass: String) {
        viewModelScope.launch() {
            if (rutubeDataBase.getDao().isAlreadyExist(login) != null) {
                rutubeDataBase.getDao().delete(RutubeMembers(login, pass))
            }
        }

    }


    fun regNewUser(login: String, pass: String) {
        viewModelScope.launch() {

            if (rutubeDataBase.getDao().isAlreadyExist(login) != null) {
                _state.value = RutubeMemberState.InvalidMember("Пользователь уже существует")
            } else {
                rutubeDataBase.getDao().insert(RutubeMembers(login, pass))
                _state.value = RutubeMemberState.ValidMember(RutubeMembers(login, pass))

            }
        }
    }

    @SuppressLint("SuspiciousIndentation")
    fun login(login: String, pass: String) {
        viewModelScope.launch() {
            if (rutubeDataBase.getDao().validateLogin(login, pass) != null) {
                _state.value = RutubeMemberState.ValidMember(RutubeMembers(login, pass))
            } else {
                _state.value = RutubeMemberState.InvalidMember("Неверный логин или пароль")
            }
        }
    }
}

