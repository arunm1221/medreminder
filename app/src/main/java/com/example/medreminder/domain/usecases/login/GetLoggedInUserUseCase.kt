package com.example.medreminder.domain.usecases.login

import com.example.medreminder.domain.model.User
import com.example.medreminder.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetLoggedInUserUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    operator fun invoke(): Flow<User?> =
        loginRepository.getAllUser().map { list -> list.firstOrNull { it.isLoggedIn } }
}