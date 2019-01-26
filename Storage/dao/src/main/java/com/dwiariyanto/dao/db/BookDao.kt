package com.dwiariyanto.dao.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import java.util.*

/**
 * Created by Dwi Ariyanto on 26/01/19.
 */


@Dao
@TypeConverters(DateConverter::class)
interface BookDao {

    @Query("select * from Book where id = :id")
    fun loadBookById(id: Int): Book

    @Query(
        "SELECT * FROM Book " +
                "INNER JOIN Loan ON Loan.book_id = Book.id " +
                "INNER JOIN User on User.id = Loan.user_id " +
                "WHERE User.name LIKE :userName"
    )
    fun findBooksBorrowedByName(userName: String): LiveData<List<Book>>

    @Query(
        ("SELECT * FROM Book " +
                "INNER JOIN Loan ON Loan.book_id = Book.id " +
                "INNER JOIN User on User.id = Loan.user_id " +
                "WHERE User.name LIKE :userName " +
                "AND Loan.endTime > :after ")
    )
    fun findBooksBorrowedByNameAfter(userName: String, after: Date): LiveData<List<Book>>

    @Query(
        ("SELECT * FROM Book " +
                "INNER JOIN Loan ON Loan.book_id = Book.id " +
                "INNER JOIN User on User.id = Loan.user_id " +
                "WHERE User.name LIKE :userName")
    )
    fun findBooksBorrowedByNameSync(userName: String): List<Book>

    @Query(
        ("SELECT * FROM Book " +
                "INNER JOIN Loan ON Loan.book_id LIKE Book.id " +
                "WHERE Loan.user_id LIKE :userId ")
    )
    fun findBooksBorrowedByUser(userId: String): LiveData<List<Book>>

    @Query(
        ("SELECT * FROM Book " +
                "INNER JOIN Loan ON Loan.book_id LIKE Book.id " +
                "WHERE Loan.user_id LIKE :userId " +
                "AND Loan.endTime > :after ")
    )
    fun findBooksBorrowedByUserAfter(userId: String, after: Date): LiveData<List<Book>>

    @Query(
        ("SELECT * FROM Book " +
                "INNER JOIN Loan ON Loan.book_id LIKE Book.id " +
                "WHERE Loan.user_id LIKE :userId ")
    )
    fun findBooksBorrowedByUserSync(userId: String): List<Book>

    @Query("SELECT * FROM Book")
    fun findAllBooks(): LiveData<List<Book>>


    @Query("SELECT * FROM Book")
    fun findAllBooksSync(): List<Book>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertBook(book: Book)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateBook(book: Book)

    @Query("DELETE FROM Book")
    fun deleteAll()
}