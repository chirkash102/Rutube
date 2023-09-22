package com.example.rutube.data

import com.example.rutube.roommodel.ViewEvents

interface RutubeRepository {

    suspend fun signUP (login:String, pass:String): ViewEvents
    suspend fun signIn (login:String, pass:String): ViewEvents
    suspend fun delete (login:String, pass:String): ViewEvents
}