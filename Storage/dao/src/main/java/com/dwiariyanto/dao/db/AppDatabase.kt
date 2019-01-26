package com.dwiariyanto.dao.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

/**
 * Created by Dwi Ariyanto on 26/01/19.
 */


@Database(entities = arrayOf(User::class, Book::class, Loan::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userModel(): UserDao
    abstract fun bookModel(): BookDao
    abstract fun loanModel(): LoanDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getInMemoryDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.inMemoryDatabaseBuilder(context.applicationContext, AppDatabase::class.java)
                    // To simplify the codelab, allow queries on the main thread.
                    // Don't do this on a real app! See PersistenceBasicSample for an example.
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}