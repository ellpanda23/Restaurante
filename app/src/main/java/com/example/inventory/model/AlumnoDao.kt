package com.example.inventory.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.inventory.model.Alumno

@Dao
interface AlumnoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlumno(alumno: Alumno) : Boolean

    @Query("SELECT * FROM alumnos")
    suspend fun getAlumno(): Alumno

}