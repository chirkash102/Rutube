package com.example.auth.data

import com.example.auth.authdatasource.AuthDatasource
import com.example.localdatasource.daos.MembersDao
import com.example.localdatasource.database.AppDataBase
import com.example.localdatasource.entity.RutubeMembers

class RutubeAuthRepositoryImpl(private val dataSource: AuthDatasource) : RutubeRepository {


    override suspend fun signUp(
        login: String,
        pass: String
    ): com.example.localdatasource.entity.RutubeMembers? {
       return dataSource.signUp(login, pass)
    }

    override suspend fun signIn(
        login: String,
        pass: String
    ): com.example.localdatasource.entity.RutubeMembers? {
        return dataSource.signIn(login, pass)
    }

    override suspend fun delete(login: String, pass: String): Boolean {
        return dataSource.delete(login, pass)

    }

    override  fun giveLogin(): String?{
        return dataSource.giveLogin()
    }
}

interface RutubeRepository {
    suspend fun signUp(login: String, pass: String): com.example.localdatasource.entity.RutubeMembers?
    suspend fun signIn(login: String, pass: String): com.example.localdatasource.entity.RutubeMembers?
    suspend fun delete(login: String, pass: String): Boolean
     fun giveLogin():String?
}