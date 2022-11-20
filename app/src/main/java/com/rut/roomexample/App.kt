package com.rut.roomexample

import android.app.Application
import androidx.room.Room
import com.rut.roomexample.database.UserDatabase

class App : Application() {
    private lateinit var db: UserDatabase

    override fun onCreate() {
        super.onCreate()

        this.db = Room.databaseBuilder(
            this,
            UserDatabase::class.java, "users"
        ).build()
    }

    fun getDb(): UserDatabase {
        return db
    }
}