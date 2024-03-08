package com.example.inventory.data

import com.example.inventory.model.Alumno
import com.example.inventory.model.AlumnoDao

class OfflineSicenetRepository(
    private val alumnoDao: AlumnoDao
) : SicenetRepository {
    override suspend fun getAcceso(m: String, p: String): Boolean {
        return true
    }

    override suspend fun getAlumno(): Alumno = alumnoDao.getAlumno()

    override suspend fun insertAlumno(alumno: Alumno) = alumnoDao.insertAlumno(alumno)
}