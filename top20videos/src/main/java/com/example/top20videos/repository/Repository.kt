package com.example.top20videos.repository

import com.example.rutube.retrofit.RutubeApi
import com.example.rutube.retrofitmodel.ResponseModel
import retrofit2.Response

class Repository(private val rutubeApi: RutubeApi):Top20Repository {
    override suspend fun getTop20Videos(): Response<ResponseModel> {
        val result = rutubeApi.getRutubeTopVideos()
        return result

    }
}



interface Top20Repository {

    suspend fun getTop20Videos():Response<ResponseModel>

}