package com.dwiariyanto.objectbox.testonetomany

import com.dwiariyanto.objectbox.BaseActivity
import com.dwiariyanto.objectbox.testonetomany.Student_.teacher
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by Dwi Ariyanto on 22/03/19.
 */
class TestOneToManyActivity : BaseActivity() {
    override fun onViewCreated() {
        layContent.addView(createViewInsertAuto())
        layContent.addView(createViewInsertReference())
        layContent.addView(createViewRemoveReferece())
        layContent.addView(createViewSetAndPutTarget())
    }

    private fun createViewInsertAuto() =
        createView("Insert Auto") {
            insertAuto()
        }

    private fun insertAuto() {
        Teacher.box.removeAll()
        Student.box.removeAll()

        val teacher = Teacher(name = "Teacher 1")
        val student = Student(name = "Student 1")
        student.teacher.target = teacher

        Student.box.put(student)

        print()
    }

    private fun print() {
        print("Teacher")
        Teacher.box.all.forEach {
            print(it)
            print(it.students)
        }

        print("Student")
        Student.box.all.forEach {
            print(it)
            print(it.teacher.target)
        }
    }

    private fun createViewInsertReference() =
        createView("Insert Reference") {
            insertReference()
        }


    private fun insertReference() {
        Teacher.box.removeAll()
        Student.box.removeAll()

        val teacher = Teacher(name = "Teacher 1")
        val id = Teacher.box.put(teacher)

        val student = Student(name = "Student 1")
        student.teacher.target = Teacher.box.get(id)

        Student.box.put(student)

        print()
    }

    private fun createViewSetAndPutTarget() =
        createView("Set and Put Target") {
            setAndPutTarget()
        }

    private fun setAndPutTarget() {
        Teacher.box.removeAll()
        Student.box.removeAll()

        val student = Student(name = "Student 1")
        var teacher = Teacher(name = "Teacher 1")

        val id = Teacher.box.put(teacher)

        teacher = Teacher.box.get(id)
        teacher.name = "Teacher Rename 2"
        Student.box.attach(student)
        student.teacher.setAndPutTarget(teacher)

        print()
    }

    private fun createViewRemoveReferece() =
        createView("Remove Reference") {
            removeReference()
        }

    private fun removeReference() {
        insertAuto()

        val student = Student.box.all.first()
        student.teacher.target = null
        Student.box.put(student)

        print()
    }


}