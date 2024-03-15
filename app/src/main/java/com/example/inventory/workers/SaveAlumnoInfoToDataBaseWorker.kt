package com.example.inventory.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.inventory.InventoryApplication
import com.example.inventory.model.Alumno
import com.google.gson.Gson

class SaveAlumnoInfoToDataBaseWorker(
    val context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        val application = context as InventoryApplication

        val offlineSicenetRepository = application.container.offlineSicenetRepository

        val alumnoJson = inputData.getString("ALUMNO_JSON")
        val gson = Gson()
        val alumno: Alumno = gson.fromJson(alumnoJson, Alumno::class.java)

        offlineSicenetRepository.insertAlumno(alumno)

        return Result.success()
    }


}