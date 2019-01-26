package com.dwiariyanto.dao.db

import android.arch.persistence.room.*
import java.util.*

/**
 * Created by Dwi Ariyanto on 26/01/19.
 */


@Entity(
    foreignKeys = arrayOf(
        ForeignKey(entity = Book::class, parentColumns = arrayOf("id"), childColumns = arrayOf("book_id")),
        ForeignKey(entity = User::class, parentColumns = arrayOf("id"), childColumns = arrayOf("user_id"))
    )
)
@TypeConverters(DateConverter::class)
open class Loan {
    @PrimaryKey
    var id: String = ""
    var startTime: Date = Date()
    var endTime: Date = Date()
    @ColumnInfo(name = "book_id")
    var bookId: String = ""
    @ColumnInfo(name = "user_id")
    var userId: String = ""
}
