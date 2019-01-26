package com.dwiariyanto.dao.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Dwi Ariyanto on 26/01/19.
 */

@Entity
class Book {
    @PrimaryKey
    var id: String = ""
    var title: String = ""
}