package com.example.inventory.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.inventory.data.NetworkSicenetRepository
import com.example.inventory.data.OfflineSicenetRepository
import com.example.inventory.model.Alumno
import kotlinx.coroutines.coroutineScope

class GetAndSaveAlumnoInfoWorker(
    context: Context,
    params: WorkerParameters,
    private val networkRepository: NetworkSicenetRepository,
    private val offlineRepository: OfflineSicenetRepository,
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result = coroutineScope {

        val alumno: Alumno = networkRepository.getAlumno()

        if(offlineRepository.insertAlumno(alumno))
        {
            return@coroutineScope Result.success()
        } else {
            return@coroutineScope Result.failure()
        }

    }
}