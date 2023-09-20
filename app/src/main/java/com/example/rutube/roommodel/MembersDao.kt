package com.example.rutube.roommodel

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
@Dao
interface MembersDao {
    @Insert
    fun insert(members: RutubeMembers)

    @Query("SELECT * FROM RutubeMembers Where login is :login and pass is :pass")
    fun validateLogin(login:String,pass:String):RutubeMembers?

    @Query("SELECT * FROM RutubeMembers Where login is :login")
    fun isAlreadyExist(login:String):RutubeMembers?

    @Query("SELECT * FROM RutubeMembers Where login is :login")
    fun validateReg2(login:String):RutubeMembers

    @Delete
    fun delete(members: RutubeMembers)

}