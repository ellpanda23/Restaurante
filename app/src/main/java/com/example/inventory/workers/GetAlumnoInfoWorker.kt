package com.example.inventory.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class GetAlumnoInfoWorker(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {
    override fun doWork(): Result {
        return Result.success()
    }
}