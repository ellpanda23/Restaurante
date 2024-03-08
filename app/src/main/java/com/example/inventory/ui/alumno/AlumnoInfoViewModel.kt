package com.example.inventory.ui.alumno

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.inventory.data.SicenetRepository
import com.example.inventory.model.Alumno

class AlumnoInfoViewModel(
    private val networkSicenetRepository: SicenetRepository,
    private val offlineSicenetRepository: SicenetRepository
) : ViewModel() {

    suspend fun getAlumno(): Alumno = networkSicenetRepository.getAlumno()
    suspend fun insertAlumno(alumno: Alumno) = offlineSicenetRepository.insertAlumno(alumno)
}