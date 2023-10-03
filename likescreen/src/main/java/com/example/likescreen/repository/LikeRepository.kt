package com.example.likescreen.repository

import com.example.likescreen.datamodel.Item
import com.example.likescreen.datasource.LikeDatasource


class LikeRepositoryImpl( private val datasource: LikeDatasource) : LikeRepository {

    override suspend fun giveLikeVideos(login: String): List<Item> {
        return datasource.giveLikeVideos(login).map { Item(it.thumbnailUrl,it.title) }
    }

    override suspend fun like(login: String, thumbnailUrl: String, title: String){
        datasource.like(login, thumbnailUrl, title)
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