package com.example.medreminder.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.medreminder.data.local.entity.MedicineEntity

@Dao
interface MedicineDao {

    @Insert
    suspend fun insertMedicine(medicineEntity: MedicineEntity)

    @Query("SELECT * FROM medicine")
    suspend fun getMedicines(): List<MedicineEntity>

    @Delete
    suspend fun delete(medicineEntity: MedicineEntity)
}