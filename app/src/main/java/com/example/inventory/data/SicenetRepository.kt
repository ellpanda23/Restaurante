package com.example.inventory.data

import com.example.inventory.model.Alumno

interface SicenetRepository {

    suspend fun getAcceso(m: String, p: String): Boolean

    suspend fun getAlumno(): Alumno

    suspend fun insertAlumno(alumno: Alumno)
}