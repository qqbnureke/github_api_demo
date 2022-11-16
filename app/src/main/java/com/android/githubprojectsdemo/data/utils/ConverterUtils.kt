package com.android.githubprojectsdemo.data.utils

import androidx.room.TypeConverter
import java.util.*

class ConverterUtils {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromListOfStrings(value: List<String>?): String? {
        return value?.joinToString(",")
    }

    @TypeConverter
    fun fromStringToList(value: String?): List<String>? {
        return value?.split(",")
    }
}