package com.example.top20videos.retrofit

import com.example.top20videos.retrofitmodel.ResponseModel
import com.google.gson.GsonBuilder
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

const val apiKey = "752bc8d6cbb846e28ed173131231306"
private val BASE_URL = "https://rutube.ru/api/"

var gson = GsonBuilder()
    .setLenient()
    .create()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create(gson))
    .baseUrl(BASE_URL)
    .build()

object RutubeRetrofit {
    val rutubeApi = retrofit.create<RutubeApi>()

}

interface RutubeApi {
    @GET("tags/video/6716/")
    suspend fun getRutubeTopVideos(): Response<ResponseModel>

}
