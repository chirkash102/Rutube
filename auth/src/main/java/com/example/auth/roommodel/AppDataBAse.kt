package com.example.auth.roommodel

import androidx.room.Database
import androidx.room.RoomDatabase

@Database([RutubeMembers::class], version = 1, exportSchema = false)
abstract class AppDataBAse : RoomDatabase() {
    abstract fun getDao(): MembersDao
}
