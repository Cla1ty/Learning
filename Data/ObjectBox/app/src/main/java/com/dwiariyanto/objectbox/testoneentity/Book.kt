package com.dwiariyanto.objectbox.testoneentity

import com.dwiariyanto.objectbox.ObjectBox
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

/**
 * Created by Dwi Ariyanto on 22/03/19.
 */
@Entity
data class Book(
    @Id var id: Long = 0L,
    var name: String
) {
    companion object {
        val create by lazy { ObjectBox.get().boxFor(Book::class.java) }
    }
}