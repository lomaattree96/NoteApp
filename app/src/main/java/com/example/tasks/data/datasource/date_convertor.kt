package com.example.tasks.data.datasource

import androidx.room.TypeConverter
import java.util.*

class DateConvertor{
    @TypeConverter
    fun TimeStampfromDate(date: Date) :Long{
        return date.time

    }

    fun DatefromTimestamp(timeStamp:Long) :Date{
        return Date(timeStamp)

    }
}