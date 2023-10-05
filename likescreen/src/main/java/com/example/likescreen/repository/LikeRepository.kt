package com.example.likescreen.repository

import com.example.likescreen.datasource.LikeDatasource
import com.example.uikit.data.Item
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class LikeRepositoryImpl(private val datasource: LikeDatasource) : LikeRepository {

    override fun giveLikeVideos(login: String): Flow<List<Item>> {
        return datasource.giveLikeVideos(login).map { it.map { Item(it.thumbnailUrl, it.title) } }
        // мапинг нужен чтоб преобразовать список из бд к списку локального дата  класа
        // первым мап тут относитсся ко флоу (таму меня лист) второй мап мапит элементы листа
    }


    override suspend fun like(login: String, thumbnailUrl: String, title: String) {
        if (datasource.isLiked(login, thumbnailUrl)) {
            datasource.delete(login, thumbnailUrl, title)
        } else
            datasource.insert(login, thumbnailUrl, title)
    }


}


interface LikeRepository {

    fun giveLikeVideos(login: String): Flow<List<Item>>
    suspend fun like(
        login: String,
        thumbnailUrl: String,
        title: String
    )

}