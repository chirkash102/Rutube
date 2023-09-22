package com.example.rutube.data

import com.example.rutube.roommodel.AppDataBAse
import com.example.rutube.roommodel.RutubeMembers
import com.example.rutube.roommodel.ViewEvents

class RutubeRepositoryImpl(dataBase: AppDataBAse) : RutubeRepository {
    private val repository = dataBase.getDao()
    override suspend fun signUP(login: String, pass: String): ViewEvents {
        return if (repository.isAlreadyExist(login) != null) {
            repository.insert(RutubeMembers(login, pass))
            ViewEvents.Error("Пользователь уже существует, быдло")
        } else {
            repository.insert(RutubeMembers(login, pass))
            ViewEvents.SuccessRegistration(RutubeMembers(login, pass))
        }
    }

    override suspend fun signIn(login: String, pass: String): ViewEvents {
        return if (repository.validateLogin(login, pass) != null) {
            ViewEvents.SuccessAuth(RutubeMembers(login, pass))
        } else {
            ViewEvents.Error("Неверный логин или пароль, лох")
        }
    }

    override suspend fun delete(login: String, pass: String): ViewEvents {
        return if (repository.isAlreadyExist(login) != null) {
            repository.delete(RutubeMembers(login, pass))
            ViewEvents.SuccessDelete(RutubeMembers(login, pass))
        } else ViewEvents.Error("Пользователя не существует, быдло")
    }
}

interface RutubeRepository {

    suspend fun signUP(login: String, pass: String): ViewEvents
    suspend fun signIn(login: String, pass: String): ViewEvents
    suspend fun delete(login: String, pass: String): ViewEvents
}