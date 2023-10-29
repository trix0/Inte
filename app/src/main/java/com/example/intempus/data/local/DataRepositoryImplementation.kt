package com.example.intempus.data.local

import com.example.intempus.domain.model.Employee
import com.example.intempus.domain.repository.DataRepository

class DataRepositoryImplementation(
    private val employeeDao: EmployeeDao
):DataRepository {
    override suspend fun getLatestCheckInTime(): Employee?{
        return employeeDao.getLatest()
    }

    override suspend fun insertNewCheckInTime(employee: Employee) {
        employeeDao.insert(employee)
    }

}