package com.app.mobileapptask.ui.fragment.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.mobileapptask.utils.Resource
import com.app.mobileapptask.utils.Status
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val auth: FirebaseAuth
) : ViewModel() {

    private val _status = MutableLiveData<Resource<String>>()
    val status: LiveData<Resource<String>> = _status

    fun createUser(emailAddress: String, password: String) {
        viewModelScope.launch(Dispatchers.Main) {
            _status.value = Resource(Status.LOADING)
            auth.createUserWithEmailAndPassword(
                emailAddress, password
            ).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _status.value = Resource(Status.SUCCESS, "User Registered Successfully")
                } else {
                    _status.value = Resource(Status.ERROR, "Registration Failed")
                }
            }
        }
    }
}