package com.example.inventory.data

import android.content.ContentValues
import android.util.Log
import com.example.inventory.model.Alumno
import com.example.inventory.model.AlumnoDao
import com.example.inventory.network.repository.SicenetApiService
import com.google.gson.Gson
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class NetworkSicenetRepository(
    private val sicenetApiService: SicenetApiService,
) : SicenetRepository {
    override suspend fun getAcceso(m: String, p: String): Boolean {
        val xml = """
            <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
              <soap:Body>
                <accesoLogin xmlns="http://tempuri.org/">
                  <strMatricula>${m}</strMatricula>
                  <strContrasenia>${p}</strContrasenia>
                  <tipoUsuario>ALUMNO</tipoUsuario>
                </accesoLogin>
              </soap:Body>
            </soap:Envelope>
            """.trimIndent()
        val requestBody=xml.toRequestBody()
        sicenetApiService.getCookies()
        val TAG = "REPOSITORY"
        try {
            var respuesta=sicenetApiService.getAcceso(requestBody).string().split("{","}")
            Log.d("RESPUESTA", respuesta.toString())
            if(respuesta.size>1){
                //val result = Gson().fromJson("{"+respuesta[1]+"}", Acceso::class.java)
                Log.d(TAG, "ENTRO AL IF Y ES: " + respuesta.toString())
                return true
            } else {
                Log.d(TAG, "ENTRO AL ELSE Y ES: false")
                return false
            }
        }catch (e: IOException){
            Log.d(TAG, "ENTRO AL EXCEPTION Y ES: false")
            return false
        }
    }

    override suspend fun getAlumno(): Alumno {
        val xml = """
            <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
              <soap:Body>
                <getAlumnoAcademicoWithLineamiento xmlns="http://tempuri.org/" />
              </soap:Body>
            </soap:Envelope>
            """.trimIndent()
        val requestBody=xml.toRequestBody()
        try {
            val respuestaInfo= sicenetApiService.getAlumno(requestBody).string().split("{","}")
            Log.d(ContentValues.TAG, respuestaInfo.toString())
            if(respuestaInfo.size>1){
                val result = Gson().fromJson("{"+respuestaInfo[1]+"}", Alumno::class.java)
                Log.d(ContentValues.TAG, result.toString())
                return result
            } else
                return return Alumno(
                    matricula = "",
                    nombre = "",
                    fechaReins = "",
                    semActual = "",
                    cdtosAcumulados = 0,
                    cdtosActuales = 0,
                    carrera = "",
                    especialidad = "",
                    modEducativo = "",
                    adeudo = "",
                    urlFoto = "",
                    adeudoDescripcion = "",
                    inscrito = false,
                    estatus = "",
                    lineamiento = 0,
                )
        }catch (e:IOException){
            return return Alumno(
                matricula = "",
                nombre = "",
                fechaReins = "",
                semActual = "",
                cdtosAcumulados = 0,
                cdtosActuales = 0,
                carrera = "",
                especialidad = "",
                modEducativo = "",
                adeudo = "",
                urlFoto = "",
                adeudoDescripcion = "",
                inscrito = false,
                estatus = "",
                lineamiento = 0,
            )
        }
    }

    override suspend fun insertAlumno(alumno: Alumno) {

    }
}