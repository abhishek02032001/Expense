package com.example.manage.util

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId


class Converters {
    @TypeConverter
    fun toDate(dateLong: Long): LocalDate? {
        return Instant.ofEpochMilli(dateLong).atZone(ZoneId.systemDefault()).toLocalDate()
    }

    @TypeConverter
    fun fromDate(date: LocalDate?): Long? {
        if (date != null) {
            val instant: Instant = date.atStartOfDay(ZoneId.systemDefault()).toInstant()
            return instant.toEpochMilli()
        }
        return null
    }
}