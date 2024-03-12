package com.example.inventory.model

import androidx.room.PrimaryKey

data class Materia(
    @PrimaryKey(autoGenerate = false)
    val clvOficial: String = "",
    val Observaciones: String = "",
    val Semipresencial: String = "",
    val Docente: String = "",
    val Jueves: String = "",
    val Miercoles: String = "",
    val Martes: String = "",
    val Lunes: String = "",
    val Viernes: String = "",
    val EstadoMateria: String = "",
    val Materia: String = "",
    val Grupo: String = ""
)