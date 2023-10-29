package com.example.intempus.domain.repository

import com.example.intempus.domain.model.Employee
import java.time.LocalDateTime

interface DataRepository {
    suspend fun getLatestCheckInTime():Employee?
    suspend fun insertNewCheckInTime(employee: Employee)
}