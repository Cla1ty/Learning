package com.dwiariyanto.dao.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.ABORT
import android.arch.persistence.room.Query
import android.arch.persistence.room.TypeConverters
import java.util.*

/**
 * Created by Dwi Ariyanto on 26/01/19.
 */


@Dao
@TypeConverters(DateConverter::class)
interface LoanDao {

    @Query("SELECT * From Loan")
    fun findAll(): LiveData<List<Loan>>
//
    @Query(
        "SELECT Loan.id, Book.title, User.name, Loan.startTime, Loan.endTime From Loan " +
                "INNER JOIN Book ON Loan.book_id = Book.id " +
                "INNER JOIN User ON Loan.user_id = User.id "
    )
    fun findAllWithUserAndBook(): LiveData<List<LoanWithUserAndBook>>

    @Query(
        ("SELECT Loan.id, Book.title as title, User.name as name, Loan.startTime, Loan.endTime " +
                "FROM Book " +
                "INNER JOIN Loan ON Loan.book_id = Book.id " +
                "INNER JOIN User on User.id = Loan.user_id " +
                "WHERE User.name LIKE :userName " +
                "AND Loan.endTime > :after ")
    )
    fun findLoansByNameAfter(userName: String, after: Date): LiveData<List<LoanWithUserAndBook>>
//
    @Insert(onConflict = ABORT)
    fun insertLoan(loan: Loan)

    @Query("DELETE FROM Loan")
    fun deleteAll()
}