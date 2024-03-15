package com.example.inventory.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class SaveAlumnoWorker(
    private val context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        return Result.success()
    }
}