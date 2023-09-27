package com.example.auth.roommodel

import androidx.room.Entity
import androidx.room.PrimaryKey

sealed class ViewEvents {
    data class SuccessAuth(val member: RutubeMembers) : ViewEvents()
    data class SuccessRegistration(val member: RutubeMembers) : ViewEvents()
    data class SuccessDelete(val deleted: Boolean) : ViewEvents()
    data class Error(val errorMessage: String) : ViewEvents()
}

@Entity
data class RutubeMembers(
    @PrimaryKey
    val login: String,
    val pass: String
)
