package com.example.auth.data

import com.example.auth.roommodel.AppDataBase
import com.example.likescreen.rommtable.LikeVideo


class LikeRepositoryImpl(dataBase: AppDataBase) : LikeRepository {
    private val dao = dataBase.getLikeDao()
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