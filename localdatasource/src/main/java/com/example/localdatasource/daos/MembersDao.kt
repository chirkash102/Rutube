package com.example.localdatasource.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.localdatasource.entity.RutubeMembers

@Dao
interface MembersDao {

    @Insert
    suspend fun insert(members: RutubeMembers)

    @Query("SELECT * FROM RutubeMembers Where login is :login and pass is :pass")
    suspend fun validateLogin(login: String, pass: String): RutubeMembers?

    @Query("SELECT * FROM RutubeMembers Where login is :login")
    suspend fun isAlreadyExist(login: String): RutubeMembers?

    @Delete
    suspend fun delete(members: RutubeMembers)
}
