package com.example.medreminder.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [MedicineEntity::class], version = 1)
abstract class AppDataBase: RoomDatabase() {

    abstract fun medicineDao(): MedicineDao
}