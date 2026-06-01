package com.example.medreminder.data.repository

import com.example.medreminder.data.local.dao.UserDao
import com.example.medreminder.data.mapper.toDomain
import com.example.medreminder.data.mapper.toEntity
import com.example.medreminder.domain.model.User
import com.example.medreminder.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LoginRepositoryImpl (
    private val dao: UserDao
) : LoginRepository{
    override suspend fun createUser(user: User) {
        dao.createUser(user.toEntity())
    }

    override fun getAllUser(): Flow<List<User>> {
        return dao.getAllUsers().map { list -> list.map { it.toDomain() } }
    }

    override suspend fun updateLogin(user: User) {
        dao.updateUser(user.toEntity())
    }
}