package com.example.medreminder.domain.usecases.onboard

import com.example.medreminder.domain.repository.OnBoardRepository
import javax.inject.Inject

class SetOnBoardUseCase @Inject constructor(
    private val onBoardRepository: OnBoardRepository
) {
    suspend operator fun invoke() = onBoardRepository.setOnBoardSeen()
}