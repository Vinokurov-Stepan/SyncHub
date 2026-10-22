package com.vinokurov.synchub.shared.domain.repository

import com.vinokurov.synchub.shared.domain.model.TaskItem
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun observeTasks(): Flow<List<TaskItem>>
    suspend fun getTaskById(id: String): TaskItem?
    suspend fun saveTask(task: TaskItem)
    suspend fun deleteTask(id: String)
    suspend fun syncWithBackend()
}
