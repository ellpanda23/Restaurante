package com.example.inventory.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.inventory.data.SicenetRepository
import com.example.inventory.model.Alumno

class LoginViewModel(
    private val networkSicenetRepository: SicenetRepository,
    private val offlineSicenetRepository: SicenetRepository
) : ViewModel(){

    var matricula by mutableStateOf("s20120157")
    var password by mutableStateOf("8Cj%_e3")

    // Actualizar matricula
    fun updateMatricula(value: String){
        matricula = value
    }

    // Actulziar password
    fun updatePassword(value: String){
        password = value
    }

    fun validate(): Boolean = matricula != "" && password != ""

    suspend fun getAccceso(m: String = matricula, p: String = password): Boolean
    {
        return networkSicenetRepository.getAcceso(m, p)
    }
    suspend fun getAlumno(): Alumno = networkSicenetRepository.getAlumno()

    suspend fun insertAlumno(alumno: Alumno) = offlineSicenetRepository.insertAlumno(alumno)
}