package com.example.rutube.roommodel

import androidx.room.Entity
import androidx.room.PrimaryKey

sealed class RutubeMemberState {
    data class ValidMember(val member: RutubeMembers) : RutubeMemberState()
    data class InvalidMember(val errorMessage: String) : RutubeMemberState()
}

@Entity
data class RutubeMembers (
    @PrimaryKey
    val login:String,
    val pass:String
) {
}