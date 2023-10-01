package com.example.auth.roommodel

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.likescreen.rommtable.LikeDao
import com.example.likescreen.rommtable.LikeVideo

@Database([RutubeMembers::class,LikeVideo::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getDao(): MembersDao
    abstract fun getLikeDao(): LikeDao
}
