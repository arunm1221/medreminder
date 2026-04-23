package com.example.medreminder.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MedicineDao {

    @Insert
    suspend fun insertMedicine(medicineEntity: MedicineEntity)

    @Query("SELECT * FROM medicine")
    suspend fun getMedicines(): List<MedicineEntity>

    @Delete
    suspend fun delete(medicineEntity: MedicineEntity)
}