package com.example.inventory.ui.alumno

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.inventory.GET_ALUMNO_INFORMATION_WORK_NAME
import com.example.inventory.TAG_OUTPUT
import com.example.inventory.data.SicenetRepository
import com.example.inventory.model.Alumno
import com.example.inventory.workers.GetAlumnoInfoWorker

class AlumnoInfoViewModel(
    private val networkSicenetRepository: SicenetRepository,
    private val offlineSicenetRepository: SicenetRepository,
    private val application: Application
) : ViewModel() {


    private val workManager = WorkManager.getInstance(application)
    internal val outputWorkInfos: LiveData<List<WorkInfo>> = workManager.getWorkInfosByTagLiveData(TAG_OUTPUT)
    suspend fun getAlumno(): Alumno {
        // Add WorkRequest to Cleanup temporary images
        var continuation = workManager
            .beginUniqueWork(
                GET_ALUMNO_INFORMATION_WORK_NAME,
                ExistingWorkPolicy.REPLACE,
                OneTimeWorkRequest.from(GetAlumnoInfoWorker::class.java)
            )

        val infoBuilder = OneTimeWorkRequestBuilder<GetAlumnoInfoWorker>()

        continuation = continuation.then(infoBuilder.build())

        // Actually start the work
        continuation.enqueue()

        return networkSicenetRepository.getAlumno()
    }
    suspend fun insertAlumno(alumno: Alumno) = offlineSicenetRepository.insertAlumno(alumno)
}