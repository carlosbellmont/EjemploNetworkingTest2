package com.example.ejemplonetworkingtest.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PersonajeModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personajeDao(): PersonajeDao
}