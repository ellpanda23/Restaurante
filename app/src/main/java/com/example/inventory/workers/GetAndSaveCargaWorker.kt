package com.example.inventory.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.inventory.InventoryApplication
import com.example.inventory.MATRICULA_USUARIO
import com.example.inventory.PASSWORD_USUARIO

class GetAndSaveCargaWorker(
    private val context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        val application = context as InventoryApplication

        val networkSicenetRepository = application.container.networkSicenetRepository

        val offlineSicenetRepository = application.container.offlineSicenetRepository

        // PUTO ERROR ESTA AQUI, SI HAGO OTRA VEZ LA LLAMADA AL GET ACCESO VUELVE A GUARDAR
        // A JALAR LAS COOKIS Y YA ME DEJA HACER LAS PETICIONES QUE SIGUEN CHINGADAMADRE
        networkSicenetRepository.getAcceso(MATRICULA_USUARIO, PASSWORD_USUARIO)

        return Result.success()

    }

}