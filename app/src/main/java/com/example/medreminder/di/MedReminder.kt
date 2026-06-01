package com.example.medreminder.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.medreminder.data.local.AppDataBase
import com.example.medreminder.data.local.dao.MedicineDao
import com.example.medreminder.data.local.dao.UserDao
import com.example.medreminder.data.local.datastore.AppDataStore
import com.example.medreminder.data.repository.LoginRepositoryImpl
import com.example.medreminder.data.repository.OnBoardRepositoryImpl
import com.example.medreminder.domain.repository.LoginRepository
import com.example.medreminder.domain.repository.OnBoardRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@HiltAndroidApp
class MedReminder: Application() {
}

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "med_reminder_db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideMedicineDao(appDataBase: AppDataBase): MedicineDao {
        return appDataBase.medicineDao()
    }

    @Provides
    fun provideUserDao(appDataBase: AppDataBase): UserDao {
        return appDataBase.userDao()
    }

    @Provides
    @Singleton
    fun provideLoginRepository(userDao: UserDao): LoginRepository {
        return LoginRepositoryImpl(userDao)
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
    ): OnBoardRepository {
        return OnBoardRepositoryImpl(preferences)
    }
}