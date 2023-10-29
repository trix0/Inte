package com.example.intempus.data.remote

import com.example.intempus.data.dto.FirstLaunchResponse
import retrofit2.http.GET

interface IntempusApi {

    @GET("mock/api/date")
    suspend fun getFirstLaunchTime(): FirstLaunchResponse

}