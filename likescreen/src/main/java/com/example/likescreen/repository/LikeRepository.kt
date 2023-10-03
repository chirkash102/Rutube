package com.example.likescreen.repository

import com.example.likescreen.datamodel.Item
import com.example.localdatasource.daos.LikeDao
import com.example.localdatasource.entity.LikeVideo


class LikeRepositoryImpl( private val dao:LikeDao) : LikeRepository {

    override suspend fun giveLikeVideos(login: String): List<Item> {
        return dao.getLikedVideos(login).map { Item(it.thumbnailUrl,it.title) }
    }

    override suspend fun like(login: String, thumbnailUrl: String, title: String){
        dao.insert(LikeVideo(login, thumbnailUrl, title))
    }


}


interface LikeRepository {

    suspend fun giveLikeVideos(login: String): List<Item>
    suspend fun like(
        login: String,
        thumbnailUrl: String,
        title: String
    )

}