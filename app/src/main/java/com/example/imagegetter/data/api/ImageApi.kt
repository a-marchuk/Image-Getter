package com.example.imagegetter.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface UnsplashApiService {
    @GET("photos/random")
    suspend fun getRandomPhoto(
        @Header("Authorization") apiKey: String,
        @Query(value = "query") query: String,
        @Query(value = "count") count: Int
    ): Response<List<UnsplashPhoto>>
}

data class UnsplashPhoto(val id: String, val urls: UnsplashPhotoUrls)

data class UnsplashPhotoUrls(val regular: String)

