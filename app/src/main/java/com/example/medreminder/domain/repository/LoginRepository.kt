package com.example.medreminder.domain.repository

import com.example.medreminder.domain.model.User
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    suspend fun createUser(user: User)

    fun getAllUser(): Flow<List<User>>

    suspend fun updateLogin(user: User)
}