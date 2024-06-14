package com.example.mypicsearch

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Singleton object responsible for creating Retrofit instances.
object RetrofitClient {

    // Base URL for the API
    private const val BASE_URL = "https://google.serper.dev/search/"

    // Function to create and configure a Retrofit instance
    fun create(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL) // Set the base URL for the Retrofit instance
            .addConverterFactory(GsonConverterFactory.create()) // Use Gson for JSON serialization and deserialization
            .build()
    }
}
