package com.example.auth.data

class RutubeRepositoryImpl(dataBase: com.example.auth.roommodel.AppDataBAse) : RutubeRepository {
    private val membersDao = dataBase.getDao()
    override suspend fun signUp(login: String, pass: String): com.example.auth.roommodel.RutubeMembers? {
        return if (membersDao.isAlreadyExist(login) != null) {
            null
        } else {
            membersDao.insert(com.example.auth.roommodel.RutubeMembers(login, pass))
            com.example.auth.roommodel.RutubeMembers(login, pass)
        }
    }

    override suspend fun signIn(login: String, pass: String): com.example.auth.roommodel.RutubeMembers? {
        return if (membersDao.validateLogin(login, pass) != null) {
            com.example.auth.roommodel.RutubeMembers(login, pass)
        } else null
    }

    override suspend fun delete(login: String, pass: String): Boolean {
        return if (membersDao.isAlreadyExist(login) != null) {
            membersDao.delete(com.example.auth.roommodel.RutubeMembers(login, pass))
            true
        } else false
    }

}

interface RutubeRepository {
    suspend fun signUp(login: String, pass: String): com.example.auth.roommodel.RutubeMembers?
    suspend fun signIn(login: String, pass: String): com.example.auth.roommodel.RutubeMembers?
    suspend fun delete(login: String, pass: String): Boolean
}