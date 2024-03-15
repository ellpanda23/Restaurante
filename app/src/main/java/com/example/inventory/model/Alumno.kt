package com.example.inventory.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "alumnos")
data class Alumno(
    @PrimaryKey(autoGenerate = false)
    var matricula:String = "",
    var nombre:String = "nombre",
    var fechaReins:String = "",
    var semActual:String = "",
    var cdtosAcumulados:Int = 0,
    var cdtosActuales:Int = 0,
    var carrera:String = "",
    var especialidad:String = "",
    var modEducativo:String = "",
    var adeudo:String = "",
    var urlFoto:String = "",
    var adeudoDescripcion:String = "",
    var inscrito:Boolean = false,
    var estatus:String = "",
    var lineamiento:Int = 0,
)
