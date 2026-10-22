package com.vinokurov.synchub.shared.domain.usecase

import com.vinokurov.synchub.shared.domain.model.TaskItem
import com.vinokurov.synchub.shared.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class GetTasksUseCase(
    private val taskRepository: TaskRepository
) {
    operator fun invoke(): Flow<List<TaskItem>> {
        return taskRepository.observeTasks()
    }
}
