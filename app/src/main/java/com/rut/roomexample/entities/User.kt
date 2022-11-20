package com.rut.roomexample.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val uId: Long = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "surname") val surname: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "age") val age: Int,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "registrationDate") val registrationDate: Instant
) {
    companion object {
        fun from(name: String, surname: String, age: Int, email: String, password: String): User {
            return User(
                name = name,
                surname = surname,
                age = age,
                email = email,
                password = password,
                registrationDate = Clock.System.now()
            )
        }
    }
}