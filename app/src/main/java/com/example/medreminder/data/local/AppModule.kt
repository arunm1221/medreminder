package com.example.medreminder.data.local

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.medreminder.data.local.datastore.AppDataStore
import com.example.medreminder.data.repository.OnBoardRepositoryImpl
import com.example.medreminder.domain.repository.OnBoardRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): AppDataBase{
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "med_reminder_db"
        ).build()
    }

    @Provides
    fun provideMedicineDao(appDataBase: AppDataBase): MedicineDao{
        return appDataBase.medicineDao()
    }

    @Provides
    @Singleton
    fun provideAppDataStore(@ApplicationContext context: Context): AppDataStore {
        return AppDataStore(context)
    }

    @Provides
    @Singleton
    fun provideOnBoardSeenRepository(
        preferences: AppDataStore
    ): OnBoardRepository{
        return OnBoardRepositoryImpl(preferences)
    }
}