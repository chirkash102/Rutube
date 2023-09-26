package com.example.rutube.data

import com.example.rutube.roommodel.AppDataBAse
import com.example.rutube.roommodel.RutubeMembers

class RutubeRepositoryImpl(dataBase: AppDataBAse) : RutubeRepository {
    private val membersDao = dataBase.getDao()
    override suspend fun signUp(login: String, pass: String): RutubeMembers? {
        return if (membersDao.isAlreadyExist(login) != null) {
            null
        } else {
            membersDao.insert(RutubeMembers(login, pass))
            RutubeMembers(login, pass)
        }
    }

    override suspend fun signIn(login: String, pass: String): RutubeMembers? {
        return if (membersDao.validateLogin(login, pass) != null) {
            RutubeMembers(login, pass)
        } else null
    }

    override suspend fun delete(login: String, pass: String): Boolean {
        return if (membersDao.isAlreadyExist(login) != null) {
            membersDao.delete(RutubeMembers(login, pass))
            true
        } else false
    }

}

interface RutubeRepository {
    suspend fun signUp(login: String, pass: String): RutubeMembers?
    suspend fun signIn(login: String, pass: String): RutubeMembers?
    suspend fun delete(login: String, pass: String): Boolean
}