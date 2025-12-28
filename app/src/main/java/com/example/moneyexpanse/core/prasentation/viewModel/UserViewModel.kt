package com.example.moneyexpanse.core.prasentation.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moneyexpanse.core.data.dataModel.User
import com.example.moneyexpanse.core.domain.authRepository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(val userRepository: UserRepository) : ViewModel() {


    private val _userState = mutableStateOf<List<User>>(emptyList())
    val userState: State<List<User>> = _userState

    fun getUser() {
        viewModelScope.launch {
           _userState.value= userRepository.getUser()
        }
    }

    fun LogOut() {
        viewModelScope.launch {
            userRepository.LogOut()
        }
    }

}