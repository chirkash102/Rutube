package com.example.localdatasource.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.localdatasource.entity.LikeVideo

@Dao
interface LikeDao {

    @Insert
    suspend fun insert(likeVideo: LikeVideo)

    @Query("SELECT * FROM LikeVideo Where login is :login")
    suspend fun getLikedVideos(login: String): List<LikeVideo>
}