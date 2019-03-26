package com.dwiariyanto.objectbox

import android.content.Intent
import com.dwiariyanto.objectbox.testoneentity.TestOneEntityActivity
import com.dwiariyanto.objectbox.testonetomany.TestOneToManyActivity
import com.dwiariyanto.objectbox.testrx.TestRxActivity
import com.dwiariyanto.objectbox.testtransaction.TestTransaction
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by Dwi Ariyanto on 22/03/19.
 */

class MainActivity : BaseActivity() {
    override fun onViewCreated() {
        layContent.addView(createViewTestOneEntity())
        layContent.addView(createViewTestOneToMany())
        layContent.addView(createViewTestTransaction())
        layContent.addView(createViewTestRx())
    }

    private fun createViewTestOneEntity() =
        createView("Test One Entity") {
            val intent = Intent(this, TestOneEntityActivity::class.java)
            startActivity(intent)
        }

    private fun createViewTestOneToMany() =
        createView("Test One To Many") {
            val intent = Intent(this, TestOneToManyActivity::class.java)
            startActivity(intent)
        }

    private fun createViewTestTransaction() =
        createView("Test Transaction") {
            val intent = Intent(this, TestTransaction::class.java)
            startActivity(intent)
        }

    private fun createViewTestRx() =
        createView("Test Rx") {
            val intent = Intent(this, TestRxActivity::class.java)
            startActivity(intent)
        }
}