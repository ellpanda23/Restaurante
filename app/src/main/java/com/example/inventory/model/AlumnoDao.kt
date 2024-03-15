package com.example.inventory.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.inventory.model.Alumno



@Dao
interface AlumnoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlumno(alumno: Alumno)

    @Delete
    suspend fun delete(alumno: Alumno)

    @Query("SELECT * FROM alumnos where matricula = :m")
    suspend fun getAlumno(m: String): Alumno

}