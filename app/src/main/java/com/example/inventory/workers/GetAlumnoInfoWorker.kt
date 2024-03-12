package com.example.inventory.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.inventory.InventoryApplication
import com.example.inventory.model.Alumno

class GetAlumnoInfoWorker(
    private val context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {

        val application = context as InventoryApplication

        val networkSicenetRepository = application.container.networkSicenetRepository

        val offlineSicenetRepository = application.container.offlineSicenetRepository

        // PUTO ERROR ESTA AQUI, SI HAGO OTRA VEZ LA LLAMADA AL GET ACCESO VUELVE A GUARDAR
        // A JALAR LAS COOKIS Y YA ME DEJA HACER LAS PETICIONES QUE SIGUEN CHINGADAMADRE
        networkSicenetRepository.getAcceso("s20120157", "8Cj%_e3")

        val alumno: Alumno = networkSicenetRepository.getAlumno()

        Log.d("WORKER", alumno.toString())

        offlineSicenetRepository.insertAlumno(alumno)

        return Result.success()

    }
}