package com.example.localdatasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.localdatasource.entity.LikeVideo
import com.example.localdatasource.entity.RutubeMembers

@Database([RutubeMembers::class, LikeVideo::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getDao(): com.example.localdatasource.daos.MembersDao
    abstract fun getLikeDao(): com.example.localdatasource.daos.LikeDao
}
