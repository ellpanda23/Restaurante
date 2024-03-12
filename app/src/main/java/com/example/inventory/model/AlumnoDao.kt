package com.example.inventory.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.inventory.model.Alumno

@Dao
interface AlumnoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlumno(alumno: Alumno)

    @Query("SELECT * FROM alumnos where matricula = 'S20120157'")
    suspend fun getAlumno(): Alumno

}