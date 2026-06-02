package com.example.medreminder.domain.usecases.login

import com.example.medreminder.domain.model.User
import com.example.medreminder.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CheckUsernameUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    operator fun invoke(userName: String): Flow<User?> =
        loginRepository.getAllUser().map { list -> list.firstOrNull { it.userName == userName } }
}