package com.dwiariyanto.dao.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.TypeConverters
import java.util.*

/**
 * Created by Dwi Ariyanto on 26/01/19.
 */

class LoanWithUserAndBook {
    var id: String = ""
    @ColumnInfo(name = "title")
    var bookTitle: String = ""
    @ColumnInfo(name = "name")
    var userName: String = ""
    @TypeConverters(DateConverter::class)
    var startTime: Date = Date()
    @TypeConverters(DateConverter::class)
    var endTime: Date = Date()
}
