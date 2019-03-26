package com.dwiariyanto.objectbox.testquery

import com.dwiariyanto.objectbox.BaseActivity
import com.dwiariyanto.objectbox.ObjectBox
import com.dwiariyanto.objectbox.runInTx
import com.dwiariyanto.objectbox.testonetomany.Student
import com.dwiariyanto.objectbox.testonetomany.Student_
import com.dwiariyanto.objectbox.testonetomany.Teacher
import io.objectbox.kotlin.query
import java.nio.file.Files.find

/**
 * Created by Dwi Ariyanto on 24/03/19.
 */
class TestQueryActivity : BaseActivity() {
    private val query = Student.box.query {
        equal(Student_.name, "Student 1")
    }

    override fun onViewCreated() {
        prepareData()

    }

    private fun simpleQuery() {
        val value = query.find()
        print(value)
    }

    private fun reuseQuery() {
        val value = query
            .setParameter(Student_.name, "Student 2")
            .find()
        print(value)
    }

    private fun limitOffset() {
        val value = query.find(0, 2)
        print(value)
    }

    private fun propertyQuery() {
        val value = query.property(Student_.name).findStrings()
        print(value)
    }
}