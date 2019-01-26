package com.dwiariyanto.dao

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dwiariyanto.dao.db.AppDatabase
import com.dwiariyanto.dao.db.DatabaseInitializer

class MainActivity : AppCompatActivity() {
    private val mDb: AppDatabase by lazy { AppDatabase.getInMemoryDatabase(this) }
    private val mViewModel: ViewModel by lazy { ViewModelProviders.of(this).get(ViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//
//        populateDb()
//        fetchData()

        subscribeBooks()
    }

    override fun onDestroy() {
        AppDatabase.destroyInstance()
        super.onDestroy()
    }

    private fun populateDb() {
        DatabaseInitializer.populateSync(mDb)
    }

    private fun fetchData() {
        val sb = StringBuilder()
        sb.append("===== User ===== \n")
        val youngUsers = mDb.userModel().findYoungerThan(35)
        for (youngUser in youngUsers) {
            sb.append("${youngUser.lastName}, ${youngUser.name} (${youngUser.age})\n")
        }
        info(sb.toString())
    }

    private fun subscribeBooks() {
        mViewModel.books.observe(this, Observer {
            it?.let { books ->
                val sb = StringBuilder()
                sb.append("===== Books ===== \n")
                for (book in books) {
                    sb.append("${book.title}\n")
                }
                info(sb.toString())
            }
        })

        mViewModel.booksYesterday.observe(this, Observer {
            it?.let { books ->
                val sb = StringBuilder()
                sb.append("===== Books Yesterday ===== \n")
                for (book in books) {
                    sb.append("${book.title}\n")
                }
                info(sb.toString())
            }
        })

        mViewModel.loans.observe(this, Observer {
            info("Loand " + it.toString())
        })
    }

    private fun info(message: String) {
        Log.i("www", message)
    }
}
