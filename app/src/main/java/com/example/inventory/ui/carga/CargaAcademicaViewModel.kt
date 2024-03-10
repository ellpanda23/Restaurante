package com.example.inventory.ui.carga

import androidx.lifecycle.ViewModel
import com.example.inventory.data.SicenetRepository
import com.example.inventory.model.Alumno
import com.example.inventory.model.Materia

class CargaAcademicaViewModel(
    private val networkSicenetRepository: SicenetRepository,
    private val offlineSicenetRepository: SicenetRepository
) : ViewModel() {

    val materias = mutableListOf<Materia>()

    suspend fun getCarga(): List<Materia> = networkSicenetRepository.getCarga()

}