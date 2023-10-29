package com.example.intempus.domain.repository

import com.example.intempus.data.dto.FirstLaunchResponse
import java.time.LocalDateTime

interface ApiRepository {
   suspend fun getFirstLaunchTime():FirstLaunchResponse
}