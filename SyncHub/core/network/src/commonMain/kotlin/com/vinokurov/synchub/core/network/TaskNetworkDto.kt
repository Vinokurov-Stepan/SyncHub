package com.vinokurov.synchub.core.network

import kotlinx.serialization.Serializable

@Serializable
data class TaskNetworkDto(
    val id: String,
    val title: String,
    val description: String,
    val isCompleted: Boolean,
    val updatedAt: Long
)
