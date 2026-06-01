package com.example.medreminder.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.medreminder.data.local.dao.MedicineDao
import com.example.medreminder.data.local.dao.UserDao
import com.example.medreminder.data.local.entity.MedicineEntity
import com.example.medreminder.data.local.entity.UserEntity


@Database(entities = [MedicineEntity::class, UserEntity::class], version = 2)
abstract class AppDataBase: RoomDatabase() {

    abstract fun medicineDao(): MedicineDao
    abstract fun userDao(): UserDao
}