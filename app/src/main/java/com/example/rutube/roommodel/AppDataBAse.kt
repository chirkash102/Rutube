package com.example.rutube.roommodel

import androidx.room.Database
import androidx.room.RoomDatabase

@Database([RutubeMembers::class], version = 1)
abstract class AppDataBAse :RoomDatabase() {
    abstract fun getDao (): MembersDao
}