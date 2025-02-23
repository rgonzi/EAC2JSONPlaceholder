package com.example.eac2jsonplaceholder.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Post(
    @SerialName(value = "userId") val userId: Int,
    @SerialName(value = "title") val title: String,
    @SerialName(value = "body") val body: String
)
