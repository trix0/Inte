package com.example.intempus.domain.use_case

import com.example.intempus.domain.extensions.formatAsDateTimeString
import com.example.intempus.domain.model.Employee
import com.example.intempus.domain.repository.DataRepository
import java.time.LocalDateTime
import javax.inject.Inject

class InsertCheckInTimeToDatabase @Inject constructor(
    private val dataRepository: DataRepository
) {
    suspend operator fun invoke(time:LocalDateTime){
        dataRepository.insertNewCheckInTime(Employee(checkInTime = time.formatAsDateTimeString()))
    }
}