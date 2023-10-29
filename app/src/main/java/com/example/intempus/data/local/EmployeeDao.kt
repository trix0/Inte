package com.example.intempus.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.intempus.domain.model.Employee

@Dao
interface EmployeeDao{

    @Query("SELECT * FROM employee ORDER BY id DESC LIMIT 1")
    suspend fun getLatest(): Employee?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(employee: Employee)
}