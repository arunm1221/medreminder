package com.example.medreminder.data.repository

import androidx.datastore.preferences.core.Preferences
import com.example.medreminder.data.local.datastore.AppDataStore
import com.example.medreminder.domain.repository.OnBoardRepository
import kotlinx.coroutines.flow.Flow

class OnBoardRepositoryImpl(
    private val preferences: AppDataStore
): OnBoardRepository{
    override fun isOnBoardSeen(): Flow<Boolean> {
        return preferences.isOnBoardSeen
    }

    override suspend fun setOnBoardSeen() {
        preferences.setOnboardSeen()
    }
}