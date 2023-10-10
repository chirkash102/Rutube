package com.example.top20videos.top20datasource

import com.example.top20videos.retrofit.RutubeApi
import com.example.top20videos.retrofitmodel.ResponseModel


class RemoteTop20DataSource(private val rutubeApi: RutubeApi) :Top20DataSource {
    override suspend fun getTop20Videos(): ResponseModel? {
        val result = rutubeApi.getRutubeTopVideos()
        return result.body()

    }
}


interface Top20DataSource {
    suspend fun getTop20Videos():ResponseModel?
}