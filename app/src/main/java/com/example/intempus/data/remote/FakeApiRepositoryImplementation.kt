package com.example.intempus.data.remote

import com.example.intempus.data.dto.FirstLaunchResponse
import com.example.intempus.domain.repository.ApiRepository
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class FakeApiRepositoryImplementation : ApiRepository {
    override suspend fun getFirstLaunchTime(): FirstLaunchResponse {
        val pattern = "yyyy-MM-dd HH:mm"
        val formatter = DateTimeFormatter.ofPattern(pattern)
        val launchTime = LocalDateTime.now().withHour(6).withMinute(30).format(formatter)
        return FirstLaunchResponse(launchTime)
    }
}