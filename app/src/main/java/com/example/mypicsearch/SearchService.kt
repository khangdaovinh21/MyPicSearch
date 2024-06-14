package com.example.mypicsearch

import com.example.mypicsearch.models.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SearchService {

    @GET("search")
    fun searchImages(
        @Header("X-API-KEY") apiKey: String,
        @Query("q") query: String,
        @Query("page") page: Int = 1, // Optional query parameter for pagination
        @Query("per_page") perPage: Int = 10 // Optional query parameter for results per page
    ): Call<ApiResponse>
}
