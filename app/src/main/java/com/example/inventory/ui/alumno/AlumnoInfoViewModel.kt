package com.example.inventory.ui.alumno

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.inventory.TAG_OUTPUT
import com.example.inventory.data.SicenetRepository
import com.example.inventory.model.Alumno
import com.example.inventory.workers.SaveAlumnoInfoToDataBaseWorker

class AlumnoInfoViewModel(
    private val networkSicenetRepository: SicenetRepository,
    private val offlineSicenetRepository: SicenetRepository,
    private val application: Application
) : ViewModel() {

    private val workManager = WorkManager.getInstance(application)
    internal val outputWorkInfos: LiveData<List<WorkInfo>> = workManager.getWorkInfosByTagLiveData(TAG_OUTPUT)

    suspend fun getAlumno(): Alumno {
        if (isConnected(application))
        {
            /*
            var continuation = workManager
                .beginUniqueWork(
                    GET_ALUMNO_INFORMATION_WORK_NAME,
                    ExistingWorkPolicy.REPLACE,
                    OneTimeWorkRequest.from(GetAlumnoInfoWorker::class.java)
                )

            val saveAlumno = OneTimeWorkRequestBuilder<SaveAlumnoInfoToDataBaseWorker>()
                .addTag("SAVE_ALUMNO_INFO")
                .build()

            continuation = continuation.then(saveAlumno)

            /*
            TODO("SEPARAR WORKERS UNO PARA JALAR LOS DATOS Y OTRO PARA INSERTAR A DB")
            val saveAlumno = OneTimeWorkRequestBuilder<SaveAlumnoWorker>()
                .addTag(SAVE_ALUMNO_INFORMATION_WORK_NAME)
                .build()

            continuation = continuation.then(saveAlumno)*/

            // Actually start the work
            continuation.enqueue()

             */

            outputWorkInfos.observeForever(object : Observer<List<WorkInfo>> {

                override fun onChanged(workInfoList: List<WorkInfo>) {
                    if (workInfoList.isNullOrEmpty()) return

                    val workInfo = workInfoList.firstOrNull { it.tags.contains("GET_ALUMNO_INFO") }
                    if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {
                        val alumnoOutputData: Data = workInfo.outputData // Obtener los datos del alumno del outputData

                        val saveAlumnoRequest = OneTimeWorkRequestBuilder<SaveAlumnoInfoToDataBaseWorker>()
                            .addTag("SAVE_ALUMNO_INFO")
                            .setInputData(alumnoOutputData) // Pasar los datos del alumno al SaveAlumnoInfoToDataBaseWorker
                            .build()

                        // Ejecutar el trabajo del SaveAlumnoInfoToDataBaseWorker
                        workManager.enqueue(saveAlumnoRequest)

                        // Después de que se complete la operación, deja de observar el LiveData
                        outputWorkInfos.removeObserver(this)
                    }
                }
            })

            if(offlineSicenetRepository.getAlumno() == null)
                return networkSicenetRepository.getAlumno()
            else
                return offlineSicenetRepository.getAlumno()
        }
        else{
            return offlineSicenetRepository.getAlumno()
        }


    }

    fun isConnected(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }
        return false
    }



}