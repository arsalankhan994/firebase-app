package com.app.mobileapptask.ui.fragment.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.mobileapptask.entity.TaskEntity
import com.app.mobileapptask.repository.local.TaskRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class TaskListViewModel @Inject constructor(
        private val taskRepository: TaskRepository,
        private val auth: FirebaseAuth
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

    fun addTaskOnFirebase(taskEntity: TaskEntity) {
        auth.currentUser?.let {
            val userId = it.uid
            val database = FirebaseDatabase.getInstance()
            val dbRef = database.getReference("TaskList")
            dbRef.child(userId).child(taskEntity.taskName.replace("Task", "").trim()).setValue(taskEntity)
        }
    }

    fun deleteTaskFromFirebase(taskEntity: TaskEntity) {
        auth.currentUser?.let {
            val userId = it.uid
            val database = FirebaseDatabase.getInstance()
            val dbRef = database.getReference("TaskList")
            dbRef.child(userId).child(taskEntity.taskName.replace("Task", "").trim()).removeValue()
        }
    }

}