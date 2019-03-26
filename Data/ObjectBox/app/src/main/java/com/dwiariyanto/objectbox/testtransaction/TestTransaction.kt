package com.dwiariyanto.objectbox.testtransaction

import com.dwiariyanto.objectbox.BaseActivity
import com.dwiariyanto.objectbox.ObjectBox
import com.dwiariyanto.objectbox.testonetomany.Student
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by Dwi Ariyanto on 24/03/19.
 */
class TestTransaction : BaseActivity() {
    override fun onViewCreated() {
        createStudent()
        layContent.addView(createViewPrintStudent())
        layContent.addView(createViewRunInTx())
        layContent.addView(createViewRunInTxAsync())
        layContent.addView(createViewInsertFail())
    }

    private fun createViewPrintStudent() =
        createView("Print Student") {
            printStudent()
        }

    private fun createViewRunInTx() =
        createView("Run in Tx") {
            runInTx()
        }

    private fun runInTx() {
        print("Out Tx")
        ObjectBox.get().runInTx {
            print("Before Clear")
            printStudent()

            Student.box.removeAll()

            print("After Clear")
            printStudent()

            Student.box.put(Student(name = "Student 1"))

            print("After Put")
            printStudent()
        }
        print("Out Tx 2")
    }

    private fun createViewRunInTxAsync() =
        createView("Run in Tx Async") {
            runInTxAsync()
        }

    private fun runInTxAsync() {
        print("Out Tx")
        ObjectBox.get().runInTxAsync(
            {
                print("Before Clear")
                printStudent()

                Student.box.removeAll()

                print("After Clear")
                printStudent()

                Student.box.put(Student(name = "Student 1"))

                print("After Put")
                printStudent()
            }, null
        )
        print("Out Tx 2")
    }

    private fun createViewInsertFail() =
        createView("Insert Fail") {
            insertFail()
        }

    private fun insertFail() {
        try {
            ObjectBox.get().runInTx {
                Student.box.removeAll()
                Student.box.put(Student(id = 100))
            }
        } catch (e: Exception) {

        }
    }

    private fun createStudent() {
        Student.box.put(Student(name = "Student 0"))
    }

    private fun printStudent() {
        Student.box.all.forEach {
            print(it)
        }
    }
}