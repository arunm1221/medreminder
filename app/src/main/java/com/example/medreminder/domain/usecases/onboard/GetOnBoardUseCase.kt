package com.example.medreminder.domain.usecases.onboard

import com.example.medreminder.domain.repository.OnBoardRepository
import javax.inject.Inject

class GetOnBoardUseCase @Inject constructor(
    private val onBoardRepository: OnBoardRepository
) {
    operator fun invoke() = onBoardRepository.isOnBoardSeen()
}