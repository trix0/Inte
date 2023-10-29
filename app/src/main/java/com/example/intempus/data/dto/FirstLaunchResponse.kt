package com.example.intempus.data.dto

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class FirstLaunchResponse(
    private val datetime: String

){
    fun fromLocalDateTime():String{
        val pattern = "yyyy-MM-dd HH:mm"
        val formatter = DateTimeFormatter.ofPattern(pattern)
        return LocalDateTime.now().withHour(6).withMinute(30).format(formatter)
    }

    fun toLocalDateTime():LocalDateTime{
        val pattern = "yyyy-MM-dd HH:mm"
        val formatter = DateTimeFormatter.ofPattern(pattern)
        return LocalDateTime.parse(datetime, formatter)
    }
}