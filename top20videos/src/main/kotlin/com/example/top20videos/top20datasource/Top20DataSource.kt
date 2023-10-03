package com.example.top20videos.top20datasource

import com.example.top20videos.retrofit.RutubeApi
import com.example.top20videos.retrofitmodel.ResponseModel
import retrofit2.Response


class RemoteTop20DataSource(private val rutubeApi: RutubeApi) :Top20DataSource {
    override suspend fun getTop20Videos(): Response<ResponseModel> {
        val result = rutubeApi.getRutubeTopVideos()
        return result

    }
}


interface Top20DataSource {
    suspend fun getTop20Videos(): Response<ResponseModel>
}