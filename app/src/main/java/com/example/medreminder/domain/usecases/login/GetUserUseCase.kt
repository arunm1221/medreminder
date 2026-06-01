package com.example.medreminder.domain.usecases.login

import com.example.medreminder.domain.repository.LoginRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
   private val loginRepository: LoginRepository
) {
    operator fun invoke(userName: String, passWord: String) =
        loginRepository.getAllUser().map { list -> list.firstOrNull { it.userName == userName && it.passWord == passWord } }
}