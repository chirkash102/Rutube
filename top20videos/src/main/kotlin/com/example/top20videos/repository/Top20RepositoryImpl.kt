package com.example.top20videos.repository

import com.example.top20videos.top20datasource.Top20DataSource
import com.example.uikit.data.Item

class Top20RepositoryImpl(private val RemoteTop20DataSource: Top20DataSource) : Top20Repository {
    override suspend fun getTop20Videos(): List<Item> {
        val body = RemoteTop20DataSource.getTop20Videos()
        if (body != null) {
            return body.results.map {
                Item(it.thumbnail_url, it.title)
            }
        } else return emptyList()
    }
}

interface Top20Repository {

    suspend fun getTop20Videos(): List<Item>

}
