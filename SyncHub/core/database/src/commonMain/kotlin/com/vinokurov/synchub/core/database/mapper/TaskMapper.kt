package com.vinokurov.synchub.core.database.mapper

import com.vinokurov.synchub.core.database.entity.TaskEntity
import com.vinokurov.synchub.shared.domain.model.SyncStatus
import com.vinokurov.synchub.shared.domain.model.TaskItem

fun TaskEntity.toDomain(): TaskItem {
    return TaskItem(
        id = id,
        title = title,
        description = description,
        isCompleted = isCompleted,
        updatedAt = updatedAt,
        syncStatus = SyncStatus.valueOf(syncStatus)
    )
}

fun TaskItem.toEntity(): TaskEntity {
    return TaskEntity(
        id = id,
        title = title,
        description = description,
        isCompleted = isCompleted,
        updatedAt = updatedAt,
        syncStatus = syncStatus.name
    )
}
