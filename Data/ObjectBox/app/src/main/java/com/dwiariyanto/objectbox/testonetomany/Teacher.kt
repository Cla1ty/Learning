package com.dwiariyanto.objectbox.testonetomany

import com.dwiariyanto.objectbox.ObjectBox
import io.objectbox.annotation.Backlink
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany

/**
 * Created by Dwi Ariyanto on 22/03/19.
 */
@Entity
data class Teacher(
    @Id var id: Long = 0L,
    var name: String = ""
) {
    @Backlink(to = "teacher")
    lateinit var students: ToMany<Student>

    companion object {

        val box by lazy { ObjectBox.get().boxFor(Teacher::class.java) }
    }
}