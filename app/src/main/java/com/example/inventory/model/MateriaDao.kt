package com.example.inventory.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
@Dao
interface MateriaDao {
    @Insert
    suspend fun insertMateria(materia: Materia)
    @Query("SELECT * FROM materias")
    suspend fun getMaterias(): List<Materia>
}