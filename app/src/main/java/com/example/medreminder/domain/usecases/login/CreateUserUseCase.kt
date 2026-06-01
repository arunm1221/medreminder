package com.example.medreminder.domain.usecases.login

import com.example.medreminder.domain.model.User
import com.example.medreminder.domain.repository.LoginRepository
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke(user: User) = loginRepository.createUser(user)
}