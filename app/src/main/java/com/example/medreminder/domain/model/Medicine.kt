package com.example.medreminder.domain.model

data class Medicine(
    val id: Int,
    val name: String,
    val time: Long,
    val dosage: String
)
