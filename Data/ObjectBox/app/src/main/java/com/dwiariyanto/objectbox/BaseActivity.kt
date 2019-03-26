package com.dwiariyanto.objectbox

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import com.dwiariyanto.objectbox.testonetomany.Student
import com.dwiariyanto.objectbox.testonetomany.Teacher

/**
 * Created by Dwi Ariyanto on 22/03/19.
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        onViewCreated()
    }

    abstract fun onViewCreated()

    fun createView(name: String, onClickAction: () -> Unit) =
        TextView(this).apply {
            text = name
            setOnClickListener {
                //                try {
                onClickAction.invoke()
//                } catch (e: Exception) {
//                    Log.e("E", e.message)
//                }
            }
            setPadding(16, 16, 16, 16)
        }

    protected fun print(any: List<Any>) {
        any.forEach {
            Log.v("A", "Print = $it")
        }
    }

    protected fun print(any: Any?) {
        Log.v("A", "Print = $any")
    }

    protected fun prepareData() {
        runInTx {
            Student.box.removeAll()
            Teacher.box.removeAll()
            repeat(5) {
                val student = Student(name = "Student $it")
                val teacher = Teacher(name = "Teacher $it")
                student.teacher.target = teacher
                Student.box.put(student)
            }
        }
    }
}