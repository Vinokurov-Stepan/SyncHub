package com.vinokurov.synchub.shared.data.repository

import com.vinokurov.synchub.core.database.dao.TaskDao
import com.vinokurov.synchub.core.database.mapper.toDomain
import com.vinokurov.synchub.core.database.mapper.toEntity
import com.vinokurov.synchub.core.network.TaskApiService
import com.vinokurov.synchub.core.network.TaskNetworkDto
import com.vinokurov.synchub.shared.domain.model.SyncStatus
import com.vinokurov.synchub.shared.domain.model.TaskItem
import com.vinokurov.synchub.shared.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskRepositoryImpl(
    private val taskDao: TaskDao,
    private val taskApiService: TaskApiService
) : TaskRepository {

    override fun observeTasks(): Flow<List<TaskItem>> {
        return taskDao.observeTasks().map { entities ->
            entities.map { entity -> entity.toDomain() }
        }
    }

    override suspend fun getTaskById(id: String): TaskItem? {
        return taskDao.getTaskById(id)?.toDomain()
    }

    override suspend fun deleteTask(id: String) {
        taskDao.deleteTask(id)
    }

    override suspend fun saveTask(task: TaskItem) {
        val taskToSave = task.copy(
            syncStatus = if (task.syncStatus == SyncStatus.SYNCED) SyncStatus.PENDING_UPDATE else task.syncStatus
        )
        taskDao.upsertTask(taskToSave.toEntity())

        try {
            taskApiService.pushTask(
                TaskNetworkDto(
                    id = taskToSave.id,
                    title = taskToSave.title,
                    description = taskToSave.description,
                    isCompleted = taskToSave.isCompleted,
                    updatedAt = taskToSave.updatedAt
                )
            )
            val syncedTask = taskToSave.copy(syncStatus = SyncStatus.SYNCED)
            taskDao.upsertTask(syncedTask.toEntity())
        } catch (e: Exception) {
        }
    }

    override suspend fun syncWithBackend() {
        try {
            val remoteTasks = taskApiService.fetchTasks()
            remoteTasks.forEach { remote ->
                val domainItem = TaskItem(
                    id = remote.id,
                    title = remote.title,
                    description = remote.description,
                    isCompleted = remote.isCompleted,
                    updatedAt = remote.updatedAt,
                    syncStatus = SyncStatus.SYNCED
                )
                taskDao.upsertTask(domainItem.toEntity())
            }
        } catch (e: Exception) {
        }
    }
}
