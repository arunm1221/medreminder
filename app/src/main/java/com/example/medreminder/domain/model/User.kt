package com.example.medreminder.domain.model

data class User(
    val id: Int = 0,
    val userName: String,
    val passWord: String,
    val isLoggedIn: Boolean = false
)
