package com.example.rutube.data

import com.example.rutube.roommodel.AppDataBAse
import com.example.rutube.roommodel.RutubeMembers

class RutubeRepositoryImpl(dataBase: AppDataBAse) : RutubeRepository {
    private val repository = dataBase.getDao()
    override suspend fun signUp(login: String, pass: String): RutubeMembers? {
        return if (repository.isAlreadyExist(login) != null) {
            null
        } else {
            repository.insert(RutubeMembers(login, pass))
            RutubeMembers(login, pass)
        }
    }

    override suspend fun signIn(login: String, pass: String): RutubeMembers? {
        return if (repository.validateLogin(login, pass) != null) {
            RutubeMembers(login, pass)
        } else null
    }

    override suspend fun delete(login: String, pass: String): RutubeMembers? {
        return if (repository.isAlreadyExist(login) != null) {
            repository.delete(RutubeMembers(login, pass))
            RutubeMembers(login, pass)
        } else null
    }

}

interface RutubeRepository {
    suspend fun signUp(login: String, pass: String): RutubeMembers?
    suspend fun signIn(login: String, pass: String): RutubeMembers?
    suspend fun delete(login: String, pass: String): RutubeMembers?
}