package com.example.intempus.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Employee(
    @PrimaryKey
    val id: Int? = null,
    val checkInTime: String
)
