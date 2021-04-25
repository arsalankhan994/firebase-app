package com.app.mobileapptask.repository.local

import androidx.lifecycle.LiveData
import com.app.mobileapptask.entity.TaskEntity
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val taskDao: TaskDao) {

    suspend fun insertTask(taskEntity: TaskEntity) {
        taskDao.insertTask(taskEntity)
    }

    suspend fun deleteTask(taskEntity: TaskEntity) {
        taskDao.deleteTask(taskEntity)
    }

    suspend fun deleteAll() {
        taskDao.deleteAll()
    }

    fun getTasks() : LiveData<List<TaskEntity>> {
        return taskDao.getTasks()
    }
}