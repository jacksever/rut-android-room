package com.rut.roomexample.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rut.roomexample.entities.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAllLiveData(): LiveData<List<User>>

    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE email = :email AND password = :password")
    suspend fun getUser(email: String, password: String): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Delete
    suspend fun delete(user: User)

    @Update
    suspend fun update(user: User)
}