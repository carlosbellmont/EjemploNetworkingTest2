package com.example.ejemplonetworkingtest.app

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ejemplonetworkingtest.model.AppDatabase

open class App : Application() {
    companion object {
        private lateinit var db : AppDatabase

        fun getDataBase() : AppDatabase = run { db }
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
    }


}