package com.example.intempus.domain.extensions

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


fun LocalDateTime.formatAsDateTimeString():String{
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    return this.format(formatter)
}


fun LocalDateTime.toLocalDateTimeFromString(time:String):LocalDateTime?{
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    return try {
       LocalDateTime.parse(time, formatter)

    } catch (e: Exception) {
        null
    }
}