package com.vinokurov.synchub.core.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class TaskApiService(
    private val httpClient: HttpClient
) {
    suspend fun fetchTasks(): List<TaskNetworkDto> {
        return httpClient.get("tasks").body()
    }

    suspend fun pushTask(task: TaskNetworkDto) {
        httpClient.post("tasks") {
            setBody(task)
        }
    }
}
