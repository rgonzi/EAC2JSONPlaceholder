package com.example.eac2jsonplaceholder.data

import com.example.eac2jsonplaceholder.network.*

interface JsonPHRepository {
    suspend fun getUsers(): List<User>
    suspend fun getPosts(userId: Int): List<Post>
}

class NetworkJsonPHRepository(private val jsonPHApiService: JsonPHApiService) : JsonPHRepository {
    override suspend fun getUsers(): List<User> {
        return jsonPHApiService.getUsers()
    }

    override suspend fun getPosts(userId: Int): List<Post> {
        return jsonPHApiService.getPosts(userId)
    }
}