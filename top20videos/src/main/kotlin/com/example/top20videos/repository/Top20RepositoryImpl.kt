package com.example.top20videos.repository

import com.example.top20videos.retrofit.RutubeApi
import com.example.top20videos.retrofit.RutubeRetrofit.rutubeApi
import com.example.top20videos.retrofitmodel.ResponseModel
import com.example.top20videos.top20datasource.Top20DataSource
import retrofit2.Response

class Top20RepositoryImpl(private val RemoteTop20DataSource:Top20DataSource) : Top20Repository {
    override suspend fun getTop20Videos(): Response<ResponseModel> {

        return RemoteTop20DataSource.getTop20Videos()
    }
}

interface Top20Repository {

    suspend fun getTop20Videos(): Response<ResponseModel>

}
