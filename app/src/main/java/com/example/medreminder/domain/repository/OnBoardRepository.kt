package com.example.medreminder.domain.repository

import kotlinx.coroutines.flow.Flow

interface OnBoardRepository {
    fun isOnBoardSeen(): Flow<Boolean>
    suspend fun setOnBoardSeen()
}