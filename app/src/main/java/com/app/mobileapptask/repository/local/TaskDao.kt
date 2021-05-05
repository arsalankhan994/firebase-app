package com.app.mobileapptask.repository.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.app.mobileapptask.entity.TaskEntity

@Dao
interface TaskDao {

    @Query("SELECT * FROM taskentity")
    fun getTasks(): LiveData<List<TaskEntity>>

    @Query("SELECT * FROM taskentity")
    fun getTaskList(): List<TaskEntity>

    @Insert
    suspend fun insertTask(taskEntity: TaskEntity)

    @Update
    suspend fun updateTask(taskEntity: TaskEntity)

    @Delete
    suspend fun deleteTask(taskEntity: TaskEntity)

    @Query("DELETE FROM taskentity")
    suspend fun deleteAll()
}