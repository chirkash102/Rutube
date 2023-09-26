package com.example.network

import com.example.network.model.ResponseModel
import retrofit2.Response

interface NetworkApi {

  suspend fun getRutubeTopVideos(): Response<ResponseModel>
}