package com.example.intempus.domain.use_case

import com.example.intempus.domain.extensions.toLocalDateTimeFromString
import com.example.intempus.domain.repository.ApiRepository
import com.example.intempus.domain.repository.DataRepository
import java.time.LocalDateTime
import javax.inject.Inject

class GetCheckInTimeUseCase @Inject constructor(
    private val dataRepository: DataRepository,
    private val apiRepository: ApiRepository
){
    suspend operator fun invoke():LocalDateTime?{
        return try {
            val databaseEntry=dataRepository.getLatestCheckInTime()
            if(databaseEntry!=null){

                return LocalDateTime.now()
                    .toLocalDateTimeFromString(time = databaseEntry.checkInTime)
            }
            return apiRepository.getFirstLaunchTime().toLocalDateTime()
        }catch (e:Exception){
            println(e)
            return null
        }
    }

}