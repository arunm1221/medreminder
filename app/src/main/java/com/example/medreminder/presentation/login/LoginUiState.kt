package com.example.medreminder.presentation.login

data class LoginUiState (
    val username: String = "",
    val passWord: String = "",
    val isUserNameEmpty: Boolean = false,
    val isPassWordEmpty: Boolean = false,
    val isLoginSuccess: Boolean = false,
    val isSignUpSuccess: Boolean = false
)