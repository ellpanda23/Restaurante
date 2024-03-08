package com.example.inventory.network.repository

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface SicenetApiService {
    @Headers(
        "Content-Type: text/xml",
        "SOAPAction: \"http://tempuri.org/accesoLogin\""
    )

    @POST("ws/wsalumnos.asmx")
    suspend fun getAcceso(
        @Body requestBody: RequestBody
    ): ResponseBody

    @Headers(
        "Content-Type: text/xml",
        "SOAPAction: \"http://tempuri.org/getAlumnoAcademicoWithLineamiento\""
    )

    @POST("ws/wsalumnos.asmx")
    suspend fun getAlumno(
        @Body requestBody: RequestBody
    ): ResponseBody

    @GET("ws/wsalumnos.asmx")
    suspend fun getCookies(): ResponseBody

    @Headers(
        "Content-Type: text/xml",
        "SOAPAction: \"http://tempuri.org/getAllKardexConPromedioByAlumno\""
    )
    @POST("ws/wsalumnos.asmx")
    suspend fun getKardex(
        @Body requestBody: RequestBody
    ): ResponseBody

    @Headers(
        "Content-Type: text/xml",
        "SOAPAction: \"http://tempuri.org/getCargaAcademicaByAlumno\""
    )

    @POST("ws/wsalumnos.asmx")
    suspend fun getCargaAcademica(
        @Body requestBody: RequestBody
    ): ResponseBody

    @Headers(
        "Content-Type: text/xml",
        "SOAPAction: \"http://tempuri.org/getAllCalifFinalByAlumnos\""
    )

    @POST("ws/wsalumnos.asmx")
    suspend fun getAllCalifFinalByAlumnos(
        @Body requestBody: RequestBody
    ): ResponseBody

    @Headers(
        "Content-Type: text/xml",
        "SOAPAction: \"http://tempuri.org/getAllCalifFinalByAlumnos\""
    )

    @POST("ws/wsalumnos.asmx")
    suspend fun getCalifUnidadesByAlumno(
        @Body requestBody: RequestBody
    ): ResponseBody
}