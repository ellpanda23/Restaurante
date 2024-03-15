package com.example.inventory.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import com.example.inventory.InventoryApplication
import com.example.inventory.MATRICULA_USUARIO
import com.example.inventory.PASSWORD_USUARIO
import com.example.inventory.model.Alumno
import com.google.gson.Gson

class GetAlumnoInfoWorker(
    private val context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {

        val application = context as InventoryApplication

        val networkSicenetRepository = application.container.networkSicenetRepository

        return try {
            networkSicenetRepository.getAcceso(MATRICULA_USUARIO, PASSWORD_USUARIO)

            val alumno: Alumno = networkSicenetRepository.getAlumno()

            Log.d("WORKER", alumno.toString())

            val gson = Gson()
            val alumnoJson = gson.toJson(alumno)

            val outputData = Data.Builder()
                .putString("ALUMNO_JSON", alumnoJson)
                .build()

            return Result.success(outputData)
        } catch (throwable: Throwable)
        {
            return Result.failure()
        }

        //offlineSicenetRepository.insertAlumno(alumno)

    }
}