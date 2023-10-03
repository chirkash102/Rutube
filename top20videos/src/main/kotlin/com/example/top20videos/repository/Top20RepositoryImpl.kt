package com.example.top20videos.repository

import com.example.top20videos.datamodel.Item
import com.example.top20videos.retrofitmodel.ResponseModel
import com.example.top20videos.top20datasource.Top20DataSource
import retrofit2.Response

class Top20RepositoryImpl(private val RemoteTop20DataSource: Top20DataSource) : Top20Repository {
    override suspend fun getTop20Videos(): List<Item> {
        val body = RemoteTop20DataSource.getTop20Videos()
        if (body != null) {
            return body.results.map {
                Item(it.thumbnail_url, it.title, true)
            }
        } else return emptyList()
    }
}

    interface Top20Repository {

        suspend fun getTop20Videos(): List<Item>

    }
