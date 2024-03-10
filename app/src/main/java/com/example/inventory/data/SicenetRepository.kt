package com.example.inventory.data

import com.example.inventory.model.Alumno
import com.example.inventory.model.Materia

interface SicenetRepository {

    suspend fun getAcceso(m: String, p: String): Boolean

    suspend fun getAlumno(): Alumno

    suspend fun getCarga(): List<Materia>

    suspend fun insertAlumno(alumno: Alumno)
}