package com.example.medreminder.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medreminder.domain.model.User
import com.example.medreminder.domain.usecases.login.CheckUsernameUseCase
import com.example.medreminder.domain.usecases.login.CreateUserUseCase
import com.example.medreminder.domain.usecases.login.GetUserUseCase
import com.example.medreminder.domain.usecases.login.UserLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val createUserUseCase: CreateUserUseCase,
    val getUserUseCase: GetUserUseCase,
    val userLoginUseCase: UserLoginUseCase,
    val checkUsernameUseCase: CheckUsernameUseCase
) : ViewModel() {

    private var _state = MutableStateFlow(LoginUiState())
    val state = _state.asStateFlow()

    private val _navigationEvent = MutableSharedFlow<LoginNavigationEvent>(extraBufferCapacity = 1)
    val navigationEvent = _navigationEvent.asSharedFlow()

    fun onUsernameChange(value: String) {
        _state.update { it.copy(username = value, isUserNameEmpty = value.isBlank(), usernameError = "") }
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
            val existing = checkUsernameUseCase(username).first()
            if (existing != null) {
                _state.update { it.copy(usernameError = "Username already exists") }
                return@launch
            }
            // Create user already logged in so the session persists across restarts
            createUserUseCase(User(userName = username, passWord = password, isLoggedIn = true))
            _state.update { it.copy(isSignUpSuccess = true, isLoginSuccess = true) }
            _navigationEvent.emit(LoginNavigationEvent.NavigateToHome)
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
                _navigationEvent.emit(LoginNavigationEvent.NavigateToHome)
            } else {
                _state.update { it.copy(usernameError = "Invalid username or password") }
            }
        }
    }
}

sealed class LoginNavigationEvent {
    object NavigateToHome : LoginNavigationEvent()
}