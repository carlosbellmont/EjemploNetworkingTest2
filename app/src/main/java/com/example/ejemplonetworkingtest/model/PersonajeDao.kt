package com.example.ejemplonetworkingtest.model

import androidx.room.*

@Dao
interface PersonajeDao {
    @Query("SELECT * FROM personajemodel")
    fun getAll(): List<PersonajeModel>

    @Query("SELECT * FROM personajemodel WHERE id == :personajeId")
    fun loadAllById(personajeId: Int): PersonajeModel

    @Query("SELECT * FROM personajemodel WHERE id IN (:personajeIds)")
    fun loadAllByIds(personajeIds: IntArray): List<PersonajeModel>

    @Query("SELECT * FROM personajemodel WHERE personaje LIKE :nombre LIMIT 1")
    fun findByName(nombre: String): PersonajeModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg personajes: PersonajeModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(personajes: List<PersonajeModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(personaje: PersonajeModel)
    
    @Delete
    fun delete(personaje: PersonajeModel)

    @Query("SELECT COUNT(*) FROM personajemodel")
    fun count(): Int

    @Query("DELETE FROM personajemodel")
    fun nukeTable()
}