package com.example.auth.likedata

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LikeDao {

    @Insert
    suspend fun insert(likeVideo: LikeVideo)

    @Query("SELECT * FROM LikeVideo Where login is :login")
    suspend fun getLikedVideos(login: String): List<LikeVideo>
}