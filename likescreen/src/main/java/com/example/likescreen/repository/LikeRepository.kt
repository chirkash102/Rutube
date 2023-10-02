package com.example.likescreen.repository

import com.example.localdatasource.daos.LikeDao
import com.example.localdatasource.entity.LikeVideo


class LikeRepositoryImpl( private val dao:LikeDao) : LikeRepository {

    override suspend fun giveLikeVideos(login: String): List<LikeVideo> {
        return dao.getLikedVideos(login)
    }

    override suspend fun like(login: String, thumbnailUrl: String, title: String){
        dao.insert(LikeVideo(login, thumbnailUrl, title))
    }


}


interface LikeRepository {

    suspend fun giveLikeVideos(login: String): List<LikeVideo>
    suspend fun like(
        login: String,
        thumbnailUrl: String,
        title: String
    )

}