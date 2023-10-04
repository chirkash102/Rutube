package com.example.likescreen.datasource

import com.example.localdatasource.daos.LikeDao
import com.example.localdatasource.entity.LikeVideo
import kotlinx.coroutines.flow.Flow


class LocalDatasource(private val dao: LikeDao) : LikeDatasource {
    override fun giveLikeVideos(login: String): Flow<List<LikeVideo>> {
        return dao.getLikedVideosFlow(login)

    }

    override suspend fun insert(login: String, thumbnailUrl: String, title: String) {
        dao.insert(LikeVideo(login, thumbnailUrl, title))

    }

    override suspend fun delete(login: String, thumbnailUrl: String, title: String) {
        dao.delete(LikeVideo(login, thumbnailUrl, title))
    }

    override suspend fun isLiked(login: String, url: String): Boolean {
        return !dao.isLiked(login, url).isEmpty()
    }
}


interface LikeDatasource {

    fun giveLikeVideos(login: String): Flow<List<LikeVideo>>
    suspend fun insert(
        login: String,
        thumbnailUrl: String,
        title: String
    )

    suspend fun delete(
        login: String,
        thumbnailUrl: String,
        title: String
    )

    suspend fun isLiked(login: String, url: String): Boolean
}