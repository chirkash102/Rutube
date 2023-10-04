package com.example.likescreen.datasource

import com.example.localdatasource.daos.LikeDao
import com.example.localdatasource.entity.LikeVideo


class LocalDatasource(private val dao: LikeDao) : LikeDatasource {
    override suspend fun giveLikeVideos(login: String): List<LikeVideo> {
        return dao.getLikedVideos(login)
    }

    override suspend fun like(login: String, thumbnailUrl: String, title: String) {
        dao.insert(LikeVideo(login, thumbnailUrl, title))
    }
}


class RemoteDataSourсe : LikeDatasource {
    override suspend fun giveLikeVideos(login: String): List<LikeVideo> {
        TODO("Not yet implemented")
    }

    override suspend fun like(login: String, thumbnailUrl: String, title: String) {
        TODO("Not yet implemented")
    }
}

interface LikeDatasource {

    suspend fun giveLikeVideos(login: String): List<LikeVideo>
    suspend fun like(
        login: String,
        thumbnailUrl: String,
        title: String
    )
}