package com.example.auth.data

import com.example.auth.authdatasource.AuthDatasource
import com.example.localdatasource.entity.RutubeMembers
import kotlinx.coroutines.flow.StateFlow

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

    override suspend fun delete(): Boolean {
        return dataSource.delete()

    }

    override suspend fun signOut(){
        dataSource.signOut()
    }

    override fun getLogin(): StateFlow<String?> {
        return dataSource.getLogin()
    }


}

interface RutubeRepository {
    suspend fun signUp(login: String, pass: String): RutubeMembers?
    suspend fun signIn(login: String, pass: String): RutubeMembers?
    suspend fun delete(): Boolean
    suspend fun signOut()
     fun getLogin(): StateFlow<String?>
}