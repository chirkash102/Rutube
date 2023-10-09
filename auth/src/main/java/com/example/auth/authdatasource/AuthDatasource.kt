package com.example.auth.authdatasource

import com.example.localdatasource.daos.MembersDao
import com.example.localdatasource.entity.RutubeMembers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class LocalAuthDatasource(private val membersDao: MembersDao) : AuthDatasource {

    private val _authFlow = MutableStateFlow<String?>(null)


    override suspend fun signUp(login: String, pass: String): RutubeMembers? {
        return if (membersDao.isAlreadyExist(login) != null) {
            null
        } else {
            _authFlow.value = login
            membersDao.insert(RutubeMembers(login, pass))
            RutubeMembers(login, pass)

        }
    }

    override suspend fun signIn(login: String, pass: String): RutubeMembers? {
        return if (membersDao.validateLogin(login, pass) != null) {
            _authFlow.value = login
            RutubeMembers(login, pass)
        } else null
    }

    override suspend fun delete(login: String, pass: String): Boolean {
        return if (membersDao.isAlreadyExist(login) != null) {
            _authFlow.value = null
            membersDao.delete(RutubeMembers(login, pass))
            true
        } else false
    }

    override fun getLogin(): StateFlow<String?> {
        return _authFlow.asStateFlow()
    }
}


interface AuthDatasource {
    suspend fun signUp(login: String, pass: String): RutubeMembers?
    suspend fun signIn(login: String, pass: String): RutubeMembers?
    suspend fun delete(login: String, pass: String): Boolean
     fun getLogin(): StateFlow<String?>
}