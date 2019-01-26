package com.dwiariyanto.dao

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import com.dwiariyanto.dao.db.AppDatabase
import com.dwiariyanto.dao.db.Book
import com.dwiariyanto.dao.db.DatabaseInitializer
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Dwi Ariyanto on 26/01/19.
 */
class ViewModel(application: Application) : AndroidViewModel(application) {
    private val mDb: AppDatabase by lazy { AppDatabase.getInMemoryDatabase(application) }
    private val dateYesterday: Date
        get() {
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.DATE, -1)
            return calendar.time
        }

    val books: LiveData<List<Book>>
        get() = mDb.bookModel().findBooksBorrowedByName("Mike")

    val booksYesterday: LiveData<List<Book>>
        get() = mDb.bookModel().findBooksBorrowedByNameAfter("Mike", dateYesterday)

    val loans: LiveData<String>
        get() {
            val loans = mDb.loanModel().findLoansByNameAfter("Mike", dateYesterday)
            return Transformations.map(loans) {
                val sb = StringBuilder()
                val simpleDateFormat = SimpleDateFormat(
                    "yyyy-MM-dd HH:mm",
                    Locale.US
                )

                for (loan in it) {
                    sb.append(
                        String.format(
                            "%s\n  (Returned: %s)\n",
                            loan.bookTitle,
                            simpleDateFormat.format(loan.endTime)
                        )
                    )
                }
                sb.toString()
            }
        }

    init {
        DatabaseInitializer.populateAsync(mDb)
    }
}