package com.example.auth.authdatasource

import com.example.localdatasource.daos.MembersDao
import com.example.localdatasource.entity.RutubeMembers


class LocalAuthDatasource(private val membersDao: MembersDao):AuthDatasource {
    private var authAcc :String? = null
    override suspend fun signUp(login: String, pass: String): RutubeMembers? {
        return if (membersDao.isAlreadyExist(login) != null) {
            null
        } else {
            membersDao.insert(com.example.localdatasource.entity.RutubeMembers(login, pass))
            com.example.localdatasource.entity.RutubeMembers(login, pass)

        }
    }

    override suspend fun signIn(login: String, pass: String): RutubeMembers? {
        return if (membersDao.validateLogin(login, pass) != null) {
            authAcc = login
            com.example.localdatasource.entity.RutubeMembers(login, pass)
        } else null
    }

    override suspend fun delete(login: String, pass: String): Boolean {
        return if (membersDao.isAlreadyExist(login) != null) {
            membersDao.delete(com.example.localdatasource.entity.RutubeMembers(login, pass))
            true
        } else false
    }

    override  fun giveLogin(): String? {
        return authAcc
    }
}


interface AuthDatasource {
    suspend fun signUp(login: String, pass: String): com.example.localdatasource.entity.RutubeMembers?
    suspend fun signIn(login: String, pass: String): com.example.localdatasource.entity.RutubeMembers?
    suspend fun delete(login: String, pass: String): Boolean
     fun giveLogin():String?

}