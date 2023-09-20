package com.example.rutube.viewmodels

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

    private val _state = MutableStateFlow<RutubeMembers?>(null)
    val state = _state.asStateFlow()

    private val _state2 = MutableStateFlow<RutubeMemberState?>(null)
    val state2 = _state2.asStateFlow()


    fun insert(login: String, pass: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (rutubeDataBase.getDao().validateReg(login) == null) {
                rutubeDataBase.getDao().insert(RutubeMembers(login, pass))
            }
        }

    }
    fun delete(login: String, pass: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (rutubeDataBase.getDao().validateReg(login) != null) {
                rutubeDataBase.getDao().delete(RutubeMembers(login, pass))
            }
        }

    }
    fun insert2(login: String, pass: String){
        viewModelScope.launch(Dispatchers.IO) {

            if (rutubeDataBase.getDao().validateReg(login) == null) {
                rutubeDataBase.getDao().insert(RutubeMembers(login, pass))
                _state2.value =  RutubeMemberState.ValidMember(RutubeMembers(login, pass))
            } else {

                _state2.value =  RutubeMemberState.InvalidMember("Пользователь уже существует")
            }
        }
    }

    fun login(login: String, pass: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (rutubeDataBase.getDao().validateLogin(login,pass) != null)
            _state2.value = RutubeMemberState.ValidMember(RutubeMembers(login, pass))
        }
    }
}

