package com.dwiariyanto.dao.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Dwi Ariyanto on 26/01/19.
 */

@Entity
class User {
    @PrimaryKey
    var id: String = ""
    var name: String = ""
    var lastName: String = ""
    var age: Int = 0
}