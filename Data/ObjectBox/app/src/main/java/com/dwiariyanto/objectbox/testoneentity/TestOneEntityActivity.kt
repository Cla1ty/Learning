package com.dwiariyanto.objectbox.testoneentity

import android.widget.TextView
import com.dwiariyanto.objectbox.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class TestOneEntityActivity : BaseActivity() {
    override fun onViewCreated() {
        layContent.addView(createViewCheckId())
        layContent.addView(createViewPutCustomId())
    }

    private fun createViewCheckId(): TextView =
        createView("Check Id") {
            checkId()
        }

    private fun createViewPutCustomId() =
        createView("Put Custom Id") {
            putCustomId()
        }

    private fun checkId() {
        Book.create.removeAll()
        Book.create.put(Book(name = "Book Test"))
        print(Book.create.all)
    }

    // error harus 0
    private fun putCustomId() {
        Book.create.removeAll()
        Book.create.put(
            Book(
                id = 100,
                name = "Book Test"
            )
        )
        print(Book.create.all)
    }
}
