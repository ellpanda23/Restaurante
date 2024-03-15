package com.example.inventory.data

import com.example.inventory.MATRICULA_USUARIO
import com.example.inventory.model.Alumno
import com.example.inventory.model.AlumnoDao
import com.example.inventory.model.Materia

class OfflineSicenetRepository(
    private val alumnoDao: AlumnoDao
) : SicenetRepository {
    override suspend fun getAcceso(m: String, p: String): Boolean {
        return true
    }

    override suspend fun getAlumno(): Alumno = alumnoDao.getAlumno(MATRICULA_USUARIO)
    override suspend fun getCarga(): List<Materia> {
        TODO("Not yet implemented")
    }

    override suspend fun insertAlumno(alumno: Alumno) = alumnoDao.insertAlumno(alumno)
}