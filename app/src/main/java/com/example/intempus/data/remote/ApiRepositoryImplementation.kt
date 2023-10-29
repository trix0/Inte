package com.example.intempus.data.remote

import com.example.intempus.data.dto.FirstLaunchResponse
import com.example.intempus.domain.repository.ApiRepository
import java.time.LocalDateTime
import javax.inject.Inject

class ApiRepositoryImplementation @Inject constructor(
    private val api:IntempusApi
) :ApiRepository {
    override suspend fun getFirstLaunchTime(): FirstLaunchResponse {
        return api.getFirstLaunchTime()
    }
}