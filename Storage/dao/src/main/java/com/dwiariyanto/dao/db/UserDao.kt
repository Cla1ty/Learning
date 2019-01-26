package com.dwiariyanto.dao.db

import android.arch.persistence.room.*

/**
 * Created by Dwi Ariyanto on 26/01/19.
 */


@Dao
interface UserDao {
    @Query("select * from user")
    fun loadAllUsers(): List<User>

    @Query("select * from user where id = :id")
    fun loadUserById(id: Int): User

    @Query("select * from user where name = :firstName and lastName = :lastName")
    fun findByNameAndLastName(firstName: String, lastName: String): List<User>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("delete from user where name like :badName OR lastName like :badName")
    fun deleteUsersByName(badName: String): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertOrReplaceUsers(vararg users: User)

    @Delete
    fun deleteUsers(user1: User, user2: User)

    @Query("SELECT * FROM User WHERE :age == :age") // TODO: Fix this!
    fun findYoungerThan(age: Int): List<User>

    @Query("SELECT * FROM User WHERE age < :age")
    fun findYoungerThanSolution(age: Int): List<User>

    @Query("DELETE FROM User")
    fun deleteAll()
}