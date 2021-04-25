package com.app.mobileapptask.repository.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.mobileapptask.entity.TaskEntity
import com.app.mobileapptask.repository.local.TaskDao

@Database(entities = [TaskEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}