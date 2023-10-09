package com.example.auth.data

import com.example.auth.authdatasource.AuthDatasource
import com.example.localdatasource.entity.RutubeMembers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map

class RutubeAuthRepositoryImpl(private val dataSource: AuthDatasource) : RutubeRepository {


    override suspend fun signUp(
        login: String,
        pass: String
    ): RutubeMembers? {
        return dataSource.signUp(login, pass)
    }

    override suspend fun signIn(
        login: String,
        pass: String
    ): RutubeMembers? {
        return dataSource.signIn(login, pass)
    }

    override suspend fun delete(login: String, pass: String): Boolean {
        return dataSource.delete(login, pass)

    }

    override fun giveLogin(): StateFlow<String?> {
        return dataSource.getLogin()
    }
}

interface RutubeRepository {
    suspend fun signUp(login: String, pass: String): RutubeMembers?
    suspend fun signIn(login: String, pass: String): RutubeMembers?
    suspend fun delete(login: String, pass: String): Boolean
     fun giveLogin(): StateFlow<String?>
}