package com.example.eac2jsonplaceholder.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName(value = "id") val id: Int,
    @SerialName(value = "name") val name: String
)
