package com.example.eac2jsonplaceholder.data

import com.example.eac2jsonplaceholder.network.JsonPHApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val jsonPHRepository: JsonPHRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl = "https://jsonplaceholder.typicode.com"

    private val json = Json { ignoreUnknownKeys = true }

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: JsonPHApiService by lazy {
        retrofit.create(JsonPHApiService::class.java)
    }

    override val jsonPHRepository: JsonPHRepository by lazy {
        NetworkJsonPHRepository(retrofitService)
    }

}