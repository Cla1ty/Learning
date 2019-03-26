package com.dwiariyanto.objectbox.entity

import com.dwiariyanto.objectbox.ObjectBox
import io.objectbox.annotation.*


/**
 * Created by Dwi Ariyanto on 20/03/19.
 */

@Entity
data class User(
    @Id
    var id: Long = 0,

    @Unique // enforce that values are unique before an entity is put:
    @Index(type = IndexType.VALUE)  // create a database index for the corresponding database column. This can improve performance when querying for that property.
    @NameInDb("USERNAME") // This allows you to rename the Java field without affecting the property name on the database level.
    var name: String? = null
) {
    @Transient // marks properties that should not be persisted, properties will also not be persisted.
    private var tempUsageCount:Int = 0

    companion object {
        val create = ObjectBox.get().boxFor(User::class.java)
    }
}