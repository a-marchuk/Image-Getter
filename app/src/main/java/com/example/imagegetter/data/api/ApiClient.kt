package com.example.imagegetter.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://source.unsplash.com/random")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val unsplashService = retrofit.create(UnsplashApiService::class.java)

    suspend fun fetchRandomPhoto(): UnsplashPhoto? {
        val response = unsplashService.getRandomPhoto(
            apiKey = "MLaj3Um1HIdq2BwLLP_JGdj0QCAKZXXujyseDSMNHnA",
            query = "nature",
            count = 1
        )
        return if (response.isSuccessful) {
            response.body()?.firstOrNull()
        } else {
            null
        }
    }
}