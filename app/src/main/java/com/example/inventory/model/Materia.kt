package com.example.inventory.model

import androidx.room.PrimaryKey

data class Materia(
    @PrimaryKey(autoGenerate = false)
    val clvOficial: String = "",
    val observaciones: String = "",
    val docente: String = "",
    val jueves: String = "",
    val miercoles: String = "",
    val martes: String = "",
    val lunes: String = "",
    val viernes: String = "",
    val estadoMateria: String = "",
    val materia: String = "",
    val grupo: String = ""
)