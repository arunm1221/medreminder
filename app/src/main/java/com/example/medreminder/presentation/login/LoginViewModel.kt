package com.example.medreminder.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medreminder.domain.model.User
import com.example.medreminder.domain.usecases.login.CreateUserUseCase
import com.example.medreminder.domain.usecases.login.GetUserUseCase
import com.example.medreminder.domain.usecases.login.UserLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val createUserUseCase: CreateUserUseCase,
    val getUserUseCase: GetUserUseCase,
    val userLoginUseCase: UserLoginUseCase
) : ViewModel() {

    private var _state = MutableStateFlow(LoginUiState())
    val state = _state.asStateFlow()

    fun onUsernameChange(value: String) {
        _state.update { it.copy(username = value, isUserNameEmpty = value.isBlank()) }
    }

    fun onPasswordChange(value: String) {
        _state.update { it.copy(passWord = value, isPassWordEmpty = value.isBlank()) }
    }

    fun signUp() {
        val username = state.value.username
        val password = state.value.passWord
        if (username.isBlank() || password.isBlank()) {
            _state.update { it.copy(isUserNameEmpty = username.isBlank(), isPassWordEmpty = password.isBlank()) }
            return
        }
        viewModelScope.launch {
            createUserUseCase(User(userName = username, passWord = password))
            _state.update { it.copy(isSignUpSuccess = true) }
        }
    }

    fun login() {
        val username = state.value.username
        val password = state.value.passWord
        if (username.isBlank() || password.isBlank()) {
            _state.update { it.copy(isUserNameEmpty = username.isBlank(), isPassWordEmpty = password.isBlank()) }
            return
        }
        viewModelScope.launch {
            val user = getUserUseCase(username, password).first()
            if (user != null) {
                userLoginUseCase(user.copy(isLoggedIn = true))
                _state.update { it.copy(isLoginSuccess = true) }
            }
        }
    }
}