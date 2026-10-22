package com.vinokurov.synchub.shared.data.repository

import com.vinokurov.synchub.core.database.dao.TaskDao
import com.vinokurov.synchub.core.database.mapper.toDomain
import com.vinokurov.synchub.core.database.mapper.toEntity
import com.vinokurov.synchub.shared.domain.model.TaskItem
import com.vinokurov.synchub.shared.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskRepositoryImpl(
    private val taskDao: TaskDao
) : TaskRepository {

    override fun observeTasks(): Flow<List<TaskItem>> {
        return taskDao.observeTasks().map { entities ->
            entities.map { entity -> entity.toDomain() }
        }
    }

    override suspend fun getTaskById(id: String): TaskItem? {
        return taskDao.getTaskById(id)?.toDomain()
    }

    override suspend fun saveTask(task: TaskItem) {
        taskDao.upsertTask(task.toEntity())
    }

    override suspend fun deleteTask(id: String) {
        taskDao.deleteTask(id)
    }

    override suspend fun syncWithBackend() {
    }
}
