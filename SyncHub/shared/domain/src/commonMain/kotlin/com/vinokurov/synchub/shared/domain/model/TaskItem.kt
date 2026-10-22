package com.vinokurov.synchub.shared.domain.model

data class TaskItem(
    val id: String,
    val title: String,
    val description: String,
    val isCompleted: Boolean,
    val updatedAt: Long,
    val syncStatus: SyncStatus
)
