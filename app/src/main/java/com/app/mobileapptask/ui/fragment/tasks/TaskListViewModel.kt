package com.app.mobileapptask.ui.fragment.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.mobileapptask.entity.TaskEntity
import com.app.mobileapptask.repository.local.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : ViewModel() {

    fun deleteTask(taskEntity: TaskEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.deleteTask(taskEntity)
        }
    }

    fun addTask(taskEntity: TaskEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.insertTask(taskEntity)
        }
    }

}