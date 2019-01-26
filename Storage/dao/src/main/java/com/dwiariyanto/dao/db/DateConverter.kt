package com.dwiariyanto.dao.db

import android.arch.persistence.room.TypeConverter
import java.util.*

/**
 * Created by Dwi Ariyanto on 26/01/19.
 */

class DateConverter {
    @TypeConverter
    fun toDate(timestamp: Long): Date {
        return Date(timestamp)
    }

    @TypeConverter
    fun toTimestamp(date: Date): Long {
        return date.time
    }
}
