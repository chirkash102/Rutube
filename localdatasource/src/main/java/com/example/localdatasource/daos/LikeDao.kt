package com.example.localdatasource.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.localdatasource.entity.LikeVideo
import com.example.localdatasource.entity.RutubeMembers
import kotlinx.coroutines.flow.Flow

@Dao
interface LikeDao {

    @Insert
    suspend fun insert(likeVideo: LikeVideo)
    @Delete
    suspend fun delete(unLikeVideo: LikeVideo)

//    @Query("SELECT * FROM LikeVideo Where login is :login")
//    suspend fun getLikedVideos(login: String): List<LikeVideo>

    @Query("SELECT * FROM LikeVideo Where login is :login")
    fun getLikedVideosFlow(login: String): Flow<List<LikeVideo>>
    @Query("SELECT * FROM LikeVideo Where login is :login  AND  thumbnailUrl= :url")
    suspend fun isLiked(login: String, url:String): List<LikeVideo>
}