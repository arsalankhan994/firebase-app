package com.app.mobileapptask.ui.fragment.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.mobileapptask.repository.local.TaskRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val taskRepository: TaskRepository
) : ViewModel() {

    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            auth.signOut()
            taskRepository.deleteAll()
        }
    }
}