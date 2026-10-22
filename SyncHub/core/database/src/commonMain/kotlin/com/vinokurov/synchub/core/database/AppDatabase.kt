package com.vinokurov.synchub.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vinokurov.synchub.core.database.dao.TaskDao
import com.vinokurov.synchub.core.database.entity.TaskEntity

@Database(entities = [TaskEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao
}
