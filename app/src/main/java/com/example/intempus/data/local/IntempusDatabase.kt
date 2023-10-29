package com.example.intempus.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.intempus.domain.model.Employee

@Database(
    entities = [Employee::class],
    version = 1
)
abstract class IntempusDatabase: RoomDatabase() {

    abstract val employeeDao:EmployeeDao
}