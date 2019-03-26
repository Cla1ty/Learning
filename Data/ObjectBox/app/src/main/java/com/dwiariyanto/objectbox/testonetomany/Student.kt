package com.dwiariyanto.objectbox.testonetomany

import com.dwiariyanto.objectbox.ObjectBox
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToOne

/**
 * Created by Dwi Ariyanto on 22/03/19.
 */
@Entity
data class Student(
    @Id var id: Long = 0L,
    var name: String = ""
) {
    lateinit var teacher: ToOne<Teacher>

    companion object {
        val box by lazy { ObjectBox.get().boxFor(Student::class.java) }
    }
}